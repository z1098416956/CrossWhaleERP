package com.neton.service.system;

import com.neton.common.CommonResult;
import com.neton.req.CreateRoleMenuVO;
import com.neton.res.SystemRoleMenuVO;

/**
 * @author: TheSunshine
 * @create: 2024-11-12 18:05
 **/
public interface WebSystemRoleMenuService {
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
