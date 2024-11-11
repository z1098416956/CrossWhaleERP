package com.neton.controller.oauth;

import com.neton.common.CommonResult;
import com.neton.req.QueryAccAccountVO;
import com.neton.service.oauth.WebOauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/web/oauth")
public class WebOauthController {

    @Autowired
    private WebOauthService webOauthService;

    @PostMapping("/login")
    public CommonResult login(@RequestBody QueryAccAccountVO accAccountVO){

        return webOauthService.login(accAccountVO);
    }

    @GetMapping("/logout")
    public CommonResult logout(){

        return webOauthService.logout();
    }
}
