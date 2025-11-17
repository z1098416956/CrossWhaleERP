package com.cross.whale.service.impl;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.SystemErrorCodeConstants;
import com.cross.whale.dao.SystemMenuDao;
import com.cross.whale.entity.SystemMenuDO;
import com.cross.whale.req.CreateSystemMenuVO;
import com.cross.whale.req.QuerySystemMenuVO;
import com.cross.whale.req.UpdateSystemMenuVO;
import com.cross.whale.res.SystemMenuDetailsVO;
import com.cross.whale.service.SystemMenuService;
import com.cross.whale.utils.SecurityUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class SystemMenuServiceImpl implements SystemMenuService {

    @Resource
    private SystemMenuDao systemMenuDao;

    /**
     * 创建菜单
     *
     * @param createSystemMenuVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult createSystemMenu(CreateSystemMenuVO createSystemMenuVO) {
        SystemMenuDO systemMenuDO = new SystemMenuDO();
        systemMenuDO.setMenuName(createSystemMenuVO.getMenuName());
        systemMenuDO.setPId(createSystemMenuVO.getPId() == null ? 0L : createSystemMenuVO.getPId());
        systemMenuDO.setMenuIcon(createSystemMenuVO.getMenuIcon());
        systemMenuDO.setMenuType(createSystemMenuVO.getMenuType());
        systemMenuDO.setCreateBy(SecurityUtils.getUserId());
        systemMenuDO.setCreateTime(LocalDateTime.now());
        systemMenuDO.setIsDeleted(0);
        systemMenuDO.setCreateByName(SecurityUtils.getUsername());
        systemMenuDao.insert(systemMenuDO);
        return CommonResult.success();
    }

    /**
     * 更新菜单
     *
     * @param updateSystemMenuVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult updateSystemMenu(UpdateSystemMenuVO updateSystemMenuVO) {
        if (updateSystemMenuVO.getId() == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_MENU_ID_IS_NULL);
        }
        SystemMenuDO systemMenuDO = systemMenuDao.selectById(updateSystemMenuVO.getId());
        if (systemMenuDO == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_MENU_IS_NULL);
        }
        BeanUtils.copyProperties(updateSystemMenuVO,systemMenuDO);
        systemMenuDO.setUpdateBy(SecurityUtils.getUserId());
        systemMenuDO.setUpdateTime(LocalDateTime.now());
        systemMenuDO.setUpdateByName(SecurityUtils.getUsername());
        systemMenuDao.updateById(systemMenuDO);
        return CommonResult.success();
    }

    /**
     * 菜单详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<SystemMenuDetailsVO> getSystemMenuDetail(Long id) {
        if (id == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_MENU_ID_IS_NULL);
        }
        SystemMenuDO systemMenuDO = systemMenuDao.selectById(id);
        if (systemMenuDO == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_MENU_IS_NULL);
        }
        SystemMenuDetailsVO vo = new SystemMenuDetailsVO();
        BeanUtils.copyProperties(systemMenuDO,vo);
        return CommonResult.success(vo);
    }

    /**
     * 菜单列表
     *
     * @param querySystemMenuVO
     * @return
     */
    @Override
    public CommonResult<List<SystemMenuDetailsVO>> getSystemMenuList(QuerySystemMenuVO querySystemMenuVO) {
        List<SystemMenuDetailsVO> systemMenuList = systemMenuDao.getSystemMenuList(querySystemMenuVO);
        return CommonResult.success(systemMenuList);
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult deletedSystemMenuInfo(Long id) {
        if (id == null){
            return CommonResult.success(SystemErrorCodeConstants.SYSTEM_MENU_ID_IS_NULL);
        }
        SystemMenuDO systemMenuDO = systemMenuDao.selectById(id);
        if (systemMenuDO == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_MENU_IS_NULL);
        }
        systemMenuDao.deleteById(id);
        return CommonResult.success();
    }
}
