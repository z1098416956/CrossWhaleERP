package com.cross.whale.service.system.impl;

import com.cross.whale.common.CommonResult;
import com.cross.whale.feign.system.SystemServiceClient;
import com.cross.whale.req.CreateRoleMenuVO;
import com.cross.whale.res.SystemRoleMenuVO;
import com.cross.whale.service.system.WebSystemRoleMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: TheSunshine
 * @create: 2024-11-12 18:05
 **/
@Service
@Slf4j
public class WebSystemRoleMenuServiceImpl implements WebSystemRoleMenuService {

    @Autowired
    private SystemServiceClient systemServiceClient;

    /**
     * 绑定角色与菜单
     *
     * @param createRoleMenuVO
     * @return
     */
    @Override
    public CommonResult createSystemRoleMenuInfo(CreateRoleMenuVO createRoleMenuVO) {
        return systemServiceClient.createSystemRoleMenuInfo(createRoleMenuVO);
    }

    /**
     * 修改角色与菜单
     *
     * @param createRoleMenuVO
     * @return
     */
    @Override
    public CommonResult updateSystemRoleMenuInfo(CreateRoleMenuVO createRoleMenuVO) {
        return systemServiceClient.updateSystemRoleMenuInfo(createRoleMenuVO);
    }

    /**
     * 删除角色菜单
     *
     * @param roleId
     * @return
     */
    @Override
    public CommonResult deleteSystemRoleMenuInfo(Long roleId) {
        return systemServiceClient.deleteSystemRoleMenuInfo(roleId);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public CommonResult deleteSystemRoleMenuInfoById(Long id) {
        return systemServiceClient.deleteSystemRoleMenuInfoById(id);
    }

    /**
     * 获取角色菜单
     *
     * @param roleId
     * @return
     */
    @Override
    public CommonResult<SystemRoleMenuVO> getSystemRoleMenuInfo(Long roleId) {
        return systemServiceClient.getSystemRoleMenuInfo(roleId);
    }
}
