package com.cross.whale.service.impl;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.SystemErrorCodeConstants;
import com.cross.whale.dao.SystemRoleMenuDao;
import com.cross.whale.entity.SystemRoleMenuDO;
import com.cross.whale.req.CreateRoleMenuVO;
import com.cross.whale.res.SystemRoleMenuVO;
import com.cross.whale.service.SystemRoleMenuService;
import com.cross.whale.utils.SecurityUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: TheSunshine
 * @create: 2024-11-11 18:10
 **/
@Service
@Slf4j
public class SystemRoleMenuServiceImpl implements SystemRoleMenuService {

    @Resource
    private SystemRoleMenuDao systemRoleMenuDao;

    /**
     * 绑定角色与菜单
     *
     * @param createRoleMenuVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult createSystemRoleMenuInfo(CreateRoleMenuVO createRoleMenuVO) {
        if (createRoleMenuVO.getRoleId() == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ROLE_ID_IS_NULL);
        }
        if (createRoleMenuVO.getMenuIds() == null || createRoleMenuVO.getMenuIds().isEmpty()){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_MENU_ID_IS_NULL);
        }
        List<SystemRoleMenuDO> list = new ArrayList<>();
        for (Long m : createRoleMenuVO.getMenuIds()){
            SystemRoleMenuDO systemRoleMenuDO = new SystemRoleMenuDO();
            systemRoleMenuDO.setCreateBy(SecurityUtils.getUserId());
            systemRoleMenuDO.setCreateTime(LocalDateTime.now());
            systemRoleMenuDO.setCreateByName(SecurityUtils.getUsername());
            systemRoleMenuDO.setIsDeleted(0);
            systemRoleMenuDO.setRoleId(createRoleMenuVO.getRoleId());
            systemRoleMenuDO.setMenuId(m);
            list.add(systemRoleMenuDO);
        }
        if (!list.isEmpty()){
            systemRoleMenuDao.insert(list);
        }
        return CommonResult.success();
    }

    /**
     * 修改角色与菜单
     *
     * @param createRoleMenuVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult updateSystemRoleMenuInfo(CreateRoleMenuVO createRoleMenuVO) {
        if (createRoleMenuVO.getRoleId() == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ROLE_ID_IS_NULL);
        }
        if (createRoleMenuVO.getMenuIds() == null || createRoleMenuVO.getMenuIds().isEmpty()){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_MENU_ID_IS_NULL);
        }
        systemRoleMenuDao.deleteByRoleId(createRoleMenuVO.getRoleId());
        return createSystemRoleMenuInfo(createRoleMenuVO);
    }

    /**
     * 删除角色菜单
     *
     * @param roleId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult deleteSystemRoleMenuInfo(Long roleId) {
        if (roleId == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ROLE_ID_IS_NULL);
        }
        systemRoleMenuDao.deleteByRoleId(roleId);
        return CommonResult.success();
    }

    /**
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult deleteSystemRoleMenuInfoById(Long id) {
        if (id == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ID_IS_NULL);
        }
        systemRoleMenuDao.deleteById(id);
        return CommonResult.success();
    }

    /**
     * 获取角色菜单
     *
     * @param roleId
     * @return
     */
    @Override
    public CommonResult<SystemRoleMenuVO> getSystemRoleMenuInfo(Long roleId) {
        if (roleId == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ROLE_ID_IS_NULL);
        }
        List<Long> longs = systemRoleMenuDao.querySystemRoleMenuInfo(roleId);
        SystemRoleMenuVO systemRoleMenuVO = new SystemRoleMenuVO();
        systemRoleMenuVO.setRoleId(roleId);
        systemRoleMenuVO.setMenuIds(longs);
        return CommonResult.success(systemRoleMenuVO);
    }
}
