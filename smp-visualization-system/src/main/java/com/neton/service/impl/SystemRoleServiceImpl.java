package com.neton.service.impl;

import com.neton.common.CommonResult;
import com.neton.common.SystemErrorCodeConstants;
import com.neton.dao.SystemRoleDao;
import com.neton.entity.SystemRoleDO;
import com.neton.req.CreateRoleVO;
import com.neton.req.QueryRoleVO;
import com.neton.req.UpdateRoleVO;
import com.neton.res.SystemRuleVO;
import com.neton.service.SystemRoleService;
import com.neton.utils.SecurityUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class SystemRoleServiceImpl implements SystemRoleService {

    @Resource
    private SystemRoleDao systemRoleDao;

    /**
     * 创建系统角色
     *
     * @param createRoleVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult createSystemRole(CreateRoleVO createRoleVO) {
        if (StringUtils.isBlank(createRoleVO.getRoleName())){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ROLE_NAME_IS_NULL);
        }
        if (StringUtils.isBlank(createRoleVO.getRoleCode())){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ROLE_CODE_IS_NULL);
        }
        List<SystemRoleDO> systemRoleDOS = systemRoleDao.querySystemRoleInfo(createRoleVO.getRoleCode());
        if (!systemRoleDOS.isEmpty()){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ROLE_CODE_IS_EXIST);
        }
        SystemRoleDO systemRoleDO = new SystemRoleDO();
        systemRoleDO.setRoleName(createRoleVO.getRoleName());
        systemRoleDO.setRoleCode(createRoleVO.getRoleCode());
        systemRoleDO.setCreateBy(SecurityUtils.getUserId());
        systemRoleDO.setCreateByName(SecurityUtils.getUsername());
        systemRoleDO.setCreateTime(LocalDateTime.now());
        systemRoleDO.setIsDeleted(0);
        systemRoleDao.insert(systemRoleDO);
        return CommonResult.success();
    }

    /**
     * 更新系统角色
     *
     * @param updateRoleVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult updateSystemRole(UpdateRoleVO updateRoleVO) {
        if (updateRoleVO.getId() == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ROLE_ID_IS_NULL);
        }
        if (StringUtils.isBlank(updateRoleVO.getRoleName())){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ROLE_NAME_IS_NULL);
        }
        if (StringUtils.isBlank(updateRoleVO.getRoleCode())){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ROLE_CODE_IS_NULL);
        }
        List<SystemRoleDO> systemRoleDOS = systemRoleDao.querySystemRoleInfo(updateRoleVO.getRoleCode());
        if (!systemRoleDOS.isEmpty()){
            SystemRoleDO systemRoleDO = systemRoleDOS.get(0);
            if (updateRoleVO.getId().compareTo(systemRoleDO.getId()) != 0){
                return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ROLE_CODE_IS_EXIST);
            }
        }
        SystemRoleDO systemRoleDO = systemRoleDao.selectById(updateRoleVO.getId());
        if (systemRoleDO == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ROLE_IS_NULL);
        }
        systemRoleDO.setRoleName(updateRoleVO.getRoleName());
        systemRoleDO.setRoleCode(updateRoleVO.getRoleCode());
        systemRoleDO.setUpdateBy(SecurityUtils.getUserId());
        systemRoleDO.setUpdateTime(LocalDateTime.now());
        systemRoleDO.setUpdateByName(SecurityUtils.getUsername());
        systemRoleDao.updateById(systemRoleDO);
        return CommonResult.success();
    }

    /**
     * 删除系统角色
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult deleteSystemRole(Long id) {
        if (id == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ROLE_ID_IS_NULL);
        }
        SystemRoleDO systemRoleDO = systemRoleDao.selectById(id);
        if (systemRoleDO == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ROLE_IS_NULL);
        }
        systemRoleDao.deleteById(systemRoleDO);
        return CommonResult.success();
    }

    /**
     * 系统角色详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<SystemRuleVO> getSystemRoleDetails(Long id) {
        if (id == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ROLE_ID_IS_NULL);
        }
        SystemRoleDO systemRoleDO = systemRoleDao.selectById(id);
        if (systemRoleDO == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ROLE_IS_NULL);
        }
        SystemRuleVO vo = new SystemRuleVO();
        vo.setId(systemRoleDO.getId());
        vo.setRoleCode(systemRoleDO.getRoleCode());
        vo.setRoleName(systemRoleDO.getRoleName());
        return CommonResult.success(vo);
    }

    /**
     * 系统角色列表
     *
     * @return
     */
    @Override
    public CommonResult<List<SystemRuleVO>> getSystemRoleList(QueryRoleVO queryRoleVO) {
        List<SystemRuleVO> systemRoleList = systemRoleDao.getSystemRoleList(queryRoleVO);
        return CommonResult.success(systemRoleList);
    }
}
