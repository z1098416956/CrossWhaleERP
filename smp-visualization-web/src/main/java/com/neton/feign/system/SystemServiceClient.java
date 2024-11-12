package com.neton.feign.system;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.FeignConfig;
import com.neton.req.*;
import com.neton.res.AccAccountVO;
import com.neton.res.SystemDeptDetailsVO;
import com.neton.res.SystemDeptTree;
import com.neton.res.UserInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author TheSunshine
 * @date 2024-10-31 14:30:04
 */
@FeignClient(name = "smp-system-service" ,configuration = FeignConfig.class)
public interface SystemServiceClient {

    /**
     * 查询用户列表
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
     * @param id
     * @return
     */
    @GetMapping("/v1/account/deleteUserById")
    public CommonResult deleteUserById(@RequestParam Long id);

    @GetMapping("/v1/account/getUserInfoById")
    public CommonResult<UserInfoVO> getUserInfoById(@RequestParam Long id);

    /**
     * 更新用户信息
     * @param updateAccAccountVO
     * @return
     */
    @PostMapping("/v1/account/updateUserInfo")
    public CommonResult updateUserInfo(@RequestBody UpdateAccAccountVO updateAccAccountVO);

    /**
     * 创建系统部门
     * @param createSystemDeptVO
     * @return
     */
    @RequestMapping(value = "/v1/system/dept/createSystemDeptInfo",method = RequestMethod.POST)
    public CommonResult createSystemDeptInfo(@RequestBody CreateSystemDeptVO createSystemDeptVO);
    /**
     * 更新系统部门
     * @param updateSystemDeptVO
     * @return
     */
    @RequestMapping(value = "/v1/system/dept/updateSystemDeptInfo",method = RequestMethod.POST)
    public CommonResult updateSystemDeptInfo(@RequestBody UpdateSystemDeptVO updateSystemDeptVO);

    /**
     * 系统部门详情
     * @param id
     * @return
     */
    @GetMapping("/v1/system/dept/getSystemDeptDetailsInfo")
    public CommonResult<SystemDeptDetailsVO> getSystemDeptDetailsInfo(@RequestParam Long id);

    /**
     * 根据id获取当前这个id的tree
     * @param id
     * @return
     */
    @GetMapping("/v1/system/dept/getSystemDeptTree")
    public CommonResult<List<SystemDeptTree>> getSystemDeptTreeById(@RequestParam Long id);
    /**
     * 查询部门信息分页
     * @param querySystemDeptVO
     * @return
     */
    @PostMapping("/v1/system/dept/getSystemDeptPage")
    public CommonResult<PageUtil<SystemDeptDetailsVO>> getSystemDeptPage(@RequestBody QuerySystemDeptVO querySystemDeptVO);

    /**
     * 删除部门信息
     * @param id
     * @return
     */
    @GetMapping("/v1/system/dept/deleteSystemDeptInfo")
    public CommonResult deleteSystemDeptInfo(@RequestParam Long id);
}
