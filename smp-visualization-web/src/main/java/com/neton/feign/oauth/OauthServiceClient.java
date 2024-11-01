package com.neton.feign.oauth;

import com.neton.common.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("smp-oauth-service")
public interface OauthServiceClient {

    @PostMapping("/login")
    public CommonResult login(@RequestBody Map<String,String> params);
}
