package com.neton.feign.system;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.FeignConfig;
import com.neton.req.CreateAccAccountVO;
import com.neton.req.QueryAccAccountVO;
import com.neton.req.UpdateAccAccountVO;
import com.neton.res.AccAccountVO;
import com.neton.res.UserInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
}
