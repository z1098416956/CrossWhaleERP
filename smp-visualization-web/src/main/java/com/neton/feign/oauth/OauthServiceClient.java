package com.neton.feign.oauth;

import com.neton.common.CommonResult;
import com.neton.req.QueryAccAccountVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("smp-oauth-service")
public interface OauthServiceClient {

    @PostMapping("/v1/oauth/login")
    public CommonResult login(@RequestBody QueryAccAccountVO params);

    @GetMapping("/v1/oauth/logout")
    public CommonResult logout();
}
