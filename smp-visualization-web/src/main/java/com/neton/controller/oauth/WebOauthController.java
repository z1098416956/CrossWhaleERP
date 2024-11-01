package com.neton.controller.oauth;

import com.neton.common.CommonResult;
import com.neton.service.oauth.WebOauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/web/oauth")
public class WebOauthController {

    @Autowired
    private WebOauthService webOauthService;

    @PostMapping("/login")
    public CommonResult login(@RequestBody Map<String,String> params){

        return webOauthService.login(params);
    }
}
