package com.cross.whale.service;

import com.cross.whale.common.CommonResult;
import com.cross.whale.req.CreateRoleMenuVO;
import com.cross.whale.res.SystemRoleMenuVO;

public interface SystemRoleMenuService {

    /**
     * 绑定角色与菜单
     * @param createRoleMenuVO
     * @return
     */
    public CommonResult createSystemRoleMenuInfo(CreateRoleMenuVO createRoleMenuVO);

    /**
     * 修改角色与菜单
     * @param createRoleMenuVO
     * @return
     */
    public CommonResult updateSystemRoleMenuInfo(CreateRoleMenuVO createRoleMenuVO);

    /**
     * 删除角色菜单
     * @param roleId
     * @return
     */
    public CommonResult deleteSystemRoleMenuInfo(Long roleId);

    /**
     *
     * @param id
     * @return
     */
    public CommonResult deleteSystemRoleMenuInfoById(Long id);

    /**
     * 获取角色菜单
     * @param roleId
     * @return
     */
    public CommonResult<SystemRoleMenuVO> getSystemRoleMenuInfo(Long roleId);
}
