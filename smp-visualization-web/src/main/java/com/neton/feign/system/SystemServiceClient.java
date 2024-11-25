package com.neton.feign.system;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.FeignConfig;
import com.neton.req.*;
import com.neton.res.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author TheSunshine
 * @date 2024-10-31 14:30:04
 */
@FeignClient(name = "smp-system-service" ,configuration = FeignConfig.class,contextId = "system")
public interface SystemServiceClient {

    /**
     * 查询用户列表
     *
     * @param queryAccAccountVO
     * @return
     */
    @PostMapping("/v1/account/queryAccountInfoList")
    public CommonResult<List<AccAccountVO>> queryAccountInfoList(@RequestBody QueryAccAccountVO queryAccAccountVO);

    @GetMapping("/v1/account/getUserInfo")
    public CommonResult<UserInfoVO> getUserInfo();

    @PostMapping("/v1/account/queryUserInfoPage")
    public CommonResult<PageUtil<UserInfoVO>> queryUserInfoPage(@RequestBody QueryAccAccountVO queryAccAccountVO);

    @PostMapping("/v1/account/createUserInfo")
    public CommonResult createUserInfo(@RequestBody CreateAccAccountVO createAccAccountVO);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @GetMapping("/v1/account/deleteUserById")
    public CommonResult deleteUserById(@RequestParam Long id);

    @GetMapping("/v1/account/getUserInfoById")
    public CommonResult<UserInfoVO> getUserInfoById(@RequestParam Long id);

    /**
     * 更新用户信息
     *
     * @param updateAccAccountVO
     * @return
     */
    @PostMapping("/v1/account/updateUserInfo")
    public CommonResult updateUserInfo(@RequestBody UpdateAccAccountVO updateAccAccountVO);

    /**
     * 创建系统部门
     *
     * @param createSystemDeptVO
     * @return
     */
    @RequestMapping(value = "/v1/system/dept/createSystemDeptInfo", method = RequestMethod.POST)
    public CommonResult createSystemDeptInfo(@RequestBody CreateSystemDeptVO createSystemDeptVO);

    /**
     * 更新系统部门
     *
     * @param updateSystemDeptVO
     * @return
     */
    @RequestMapping(value = "/v1/system/dept/updateSystemDeptInfo", method = RequestMethod.POST)
    public CommonResult updateSystemDeptInfo(@RequestBody UpdateSystemDeptVO updateSystemDeptVO);

    /**
     * 系统部门详情
     *
     * @param id
     * @return
     */
    @GetMapping("/v1/system/dept/getSystemDeptDetailsInfo")
    public CommonResult<SystemDeptDetailsVO> getSystemDeptDetailsInfo(@RequestParam Long id);

    /**
     * 根据id获取当前这个id的tree
     *
     * @param id
     * @return
     */
    @GetMapping("/v1/system/dept/getSystemDeptTree")
    public CommonResult<List<SystemDeptTree>> getSystemDeptTreeById(@RequestParam Long id);

    /**
     * 查询部门信息分页
     *
     * @param querySystemDeptVO
     * @return
     */
    @PostMapping("/v1/system/dept/getSystemDeptPage")
    public CommonResult<PageUtil<SystemDeptDetailsVO>> getSystemDeptPage(@RequestBody QuerySystemDeptVO querySystemDeptVO);

    /**
     * 删除部门信息
     *
     * @param id
     * @return
     */
    @GetMapping("/v1/system/dept/deleteSystemDeptInfo")
    public CommonResult deleteSystemDeptInfo(@RequestParam Long id);

    /**
     * 创建菜单
     *
     * @param createSystemMenuVO
     * @return
     */
    @PostMapping("/v1/system/menu/createSystemMenu")
    public CommonResult createSystemMenu(@RequestBody CreateSystemMenuVO createSystemMenuVO);

    /**
     * 更新菜单
     *
     * @param updateSystemMenuVO
     * @return
     */
    @PostMapping("/v1/system/menu/updateSystemMenu")
    public CommonResult updateSystemMenu(@RequestBody UpdateSystemMenuVO updateSystemMenuVO);

    /**
     * 菜单详情
     *
     * @param id
     * @return
     */
    @GetMapping("/v1/system/menu/getSystemMenuDetail")
    public CommonResult<SystemMenuDetailsVO> getSystemMenuDetail(@RequestParam Long id);

    /**
     * 菜单列表
     *
     * @param querySystemMenuVO
     * @return
     */
    @PostMapping("/v1/system/menu/getSystemMenuList")
    public CommonResult<List<SystemMenuDetailsVO>> getSystemMenuList(@RequestBody QuerySystemMenuVO querySystemMenuVO);

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @GetMapping("/v1/system/menu/deletedSystemMenuInfo")
    public CommonResult deletedSystemMenuInfo(@RequestParam Long id);


    /**
     * 绑定角色与菜单
     * @param createRoleMenuVO
     * @return
     */
    @PostMapping("/v1/system/roleMenu/createSystemRoleMenuInfo")
    public CommonResult createSystemRoleMenuInfo(@RequestBody CreateRoleMenuVO createRoleMenuVO);

    /**
     * 修改角色与菜单
     * @param createRoleMenuVO
     * @return
     */
    @PostMapping("/v1/system/roleMenu/updateSystemRoleMenuInfo")
    public CommonResult updateSystemRoleMenuInfo(@RequestBody CreateRoleMenuVO createRoleMenuVO);

    /**
     * 删除角色菜单
     * @param roleId
     * @return
     */
    @GetMapping("/v1/system/roleMenu/deleteSystemRoleMenuInfo")
    public CommonResult deleteSystemRoleMenuInfo(@RequestParam Long roleId);

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/v1/system/roleMenu/deleteSystemRoleMenuInfoById")
    public CommonResult deleteSystemRoleMenuInfoById(Long id);

    /**
     * 获取角色菜单
     * @param roleId
     * @return
     */
    @GetMapping("/v1/system/roleMenu/getSystemRoleMenuInfo")
    public CommonResult<SystemRoleMenuVO> getSystemRoleMenuInfo(@RequestParam Long roleId);


    /**
     * 创建系统角色
     * @param createRoleVO
     * @return
     */
    @RequestMapping(value = "/v1/system/role/createSystemRole",method = RequestMethod.POST)
    public CommonResult createSystemRole(@RequestBody CreateRoleVO createRoleVO);

    /**
     * 更新系统角色
     * @param updateRoleVO
     * @return
     */
    @PostMapping("/v1/system/role/updateSystemRole")
    public CommonResult updateSystemRole(@RequestBody UpdateRoleVO updateRoleVO);

    /**
     * 删除系统角色
     * @param id
     * @return
     */
    @GetMapping("/v1/system/role/deleteSystemRole")
    public CommonResult deleteSystemRole(@RequestParam Long id);


    /**
     * 系统角色详情
     * @param id
     * @return
     */
    @GetMapping("/v1/system/role/getSystemRoleDetails")
    public CommonResult<SystemRuleVO> getSystemRoleDetails(@RequestParam Long id);

    /**
     * 系统角色列表
     * @return
     */
    @PostMapping("/v1/system/role/getSystemRoleList")
    public CommonResult<List<SystemRuleVO>> getSystemRoleList(@RequestBody QueryRoleVO queryRoleVO);

    @PostMapping("/v1/system/role/querySystemRolePage")
    public CommonResult<PageUtil<SystemRuleVO>> querySystemRolePage(@RequestBody QueryRoleVO queryRoleVO);

    @GetMapping("/v1/system/role/getUserRoleInfo")
    public CommonResult<List<SystemRuleVO>> getUserRoleInfo(@RequestParam Long id);
}
