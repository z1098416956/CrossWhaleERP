package com.cross.whale.feign.oauth;

import com.cross.whale.common.CommonResult;
import com.cross.whale.feign.FeignConfig;
import com.cross.whale.req.QueryAccAccountVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "cross-whale-oauth", configuration = FeignConfig.class)
public interface OauthServiceClient {

    @PostMapping("/v1/oauth/login")
    public CommonResult login(@RequestBody QueryAccAccountVO params);

    @GetMapping("/v1/oauth/logout")
    public CommonResult logout();
}
