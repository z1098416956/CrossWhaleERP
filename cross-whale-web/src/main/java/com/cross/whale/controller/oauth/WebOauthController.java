package com.cross.whale.controller.oauth;

import com.cross.whale.common.CommonResult;
import com.cross.whale.req.QueryAccAccountVO;
import com.cross.whale.service.oauth.WebOauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
