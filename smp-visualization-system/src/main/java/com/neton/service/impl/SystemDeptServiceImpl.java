package com.neton.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.common.SystemErrorCodeConstants;
import com.neton.dao.SystemDeptDao;
import com.neton.entity.SystemDeptDO;
import com.neton.req.CreateSystemDeptVO;
import com.neton.req.QuerySystemDeptVO;
import com.neton.req.UpdateSystemDeptVO;
import com.neton.res.SystemDeptDetailsVO;
import com.neton.res.SystemDeptTree;
import com.neton.service.SystemDeptService;
import com.neton.utils.SecurityUtils;
import com.neton.utils.tree.TreeUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class SystemDeptServiceImpl implements SystemDeptService {

    @Resource
    private SystemDeptDao systemDeptDao;


    /**
     * 创建系统部门
     *
     * @param createSystemDeptVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult createSystemDeptInfo(CreateSystemDeptVO createSystemDeptVO) {
        if (createSystemDeptVO.getPid() == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_SYSTEM_ID_IS_ERR);
        }

        if (createSystemDeptVO.getPid().compareTo(0L) > 0){
            SystemDeptDO pSystemDeptDO = systemDeptDao.selectById(createSystemDeptVO.getPid());
            if (pSystemDeptDO == null){
                return CommonResult.error(SystemErrorCodeConstants.SYSTEM_P_ID_IS_ERR);
            }
        }

        SystemDeptDO deptDO = new SystemDeptDO();
        deptDO.setDeptName(createSystemDeptVO.getDeptName());
        deptDO.setCreateBy(SecurityUtils.getUserId());
        deptDO.setPId(createSystemDeptVO.getPid());
        deptDO.setCreateTime(LocalDateTime.now());
        deptDO.setCreateByName(SecurityUtils.getUsername());
        deptDO.setIsDeleted(0);
        systemDeptDao.insert(deptDO);
        return CommonResult.success();
    }

    /**
     * 更新系统部门
     *
     * @param updateSystemDeptVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult updateSystemDeptInfo(UpdateSystemDeptVO updateSystemDeptVO) {
        if (updateSystemDeptVO.getPid() == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_SYSTEM_ID_IS_ERR);
        }
        if (updateSystemDeptVO.getId() == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_USER_ID_IS_NULL);
        }
        if (StringUtils.isBlank(updateSystemDeptVO.getDeptName())){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_DEPT_NAME_IS_NULL);
        }
        SystemDeptDO deptDO = systemDeptDao.selectById(updateSystemDeptVO.getId());
        if (deptDO == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_DEPT_IS_NULL);
        }
        if (updateSystemDeptVO.getPid().compareTo(0L) > 0){
            SystemDeptDO pSystemDeptDO = systemDeptDao.selectById(updateSystemDeptVO.getPid());
            if (pSystemDeptDO == null){
                return CommonResult.error(SystemErrorCodeConstants.SYSTEM_P_ID_IS_ERR);
            }
        }
        BeanUtils.copyProperties(updateSystemDeptVO,deptDO);
        deptDO.setUpdateBy(SecurityUtils.getUserId());
        deptDO.setPId(updateSystemDeptVO.getPid());
        deptDO.setUpdateTime(LocalDateTime.now());
        deptDO.setUpdateByName(SecurityUtils.getUsername());
        systemDeptDao.updateById(deptDO);
        return CommonResult.success();
    }

    /**
     * 系统部门详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<SystemDeptDetailsVO> getSystemDeptDetailsInfo(Long id) {
        if (id == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_USER_ID_IS_NULL);
        }
        SystemDeptDO deptDO = systemDeptDao.selectById(id);
        if (deptDO == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_DEPT_IS_NULL);
        }
        SystemDeptDetailsVO systemDeptDetailsVO = new SystemDeptDetailsVO();
        BeanUtils.copyProperties(deptDO,systemDeptDetailsVO);
        systemDeptDetailsVO.setPid(deptDO.getPId());
        return CommonResult.success(systemDeptDetailsVO);
    }

    /**
     * 根据id获取当前这个id的tree
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<List<SystemDeptTree>> getSystemDeptTreeById(Long id) {
        List<SystemDeptTree> list = systemDeptDao.querySystemDeptAll();
        return CommonResult.success(list);
    }

    /**
     * 查询部门信息分页
     *
     * @param querySystemDeptVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<SystemDeptDetailsVO>> getSystemDeptPage(QuerySystemDeptVO querySystemDeptVO) {
        IPage<SystemDeptDetailsVO> page = new Page<>();
        page.setSize(querySystemDeptVO.getSize());
        page.setCurrent(querySystemDeptVO.getPage());
        IPage<SystemDeptDetailsVO> iPage = systemDeptDao.getSystemDeptPage(page, querySystemDeptVO);
        PageUtil<SystemDeptDetailsVO> pageUtil = new PageUtil<>();
        pageUtil.setPageList(iPage.getRecords());
        pageUtil.setTotal(iPage.getTotal());
        return CommonResult.success(pageUtil);
    }

    /**
     * 删除部门信息
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult deleteSystemDeptInfo(Long id) {
        if (id == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_USER_ID_IS_NULL);
        }
        SystemDeptDO deptDO = systemDeptDao.selectById(id);
        if (deptDO == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_DEPT_IS_NULL);
        }
        systemDeptDao.deleteById(id);
        return CommonResult.success();
    }
}
