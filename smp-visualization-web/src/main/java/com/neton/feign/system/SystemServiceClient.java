package com.neton.feign.system;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.FeignConfig;
import com.neton.req.CreateAccAccountVO;
import com.neton.req.QueryAccAccountVO;
import com.neton.res.AccAccountVO;
import com.neton.res.UserInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
}
