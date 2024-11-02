package com.neton.controller.system;

import com.neton.common.CommonResult;
import com.neton.req.QueryAccAccountVO;
import com.neton.res.AccAccountVO;
import com.neton.res.UserInfoVO;
import com.neton.service.system.WebAccAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author TheSunshine
 * @date 2024-10-31 15:26:33
 */
@RestController
@RequestMapping("/v1/web/account")
public class WebAccAccountController {

    @Autowired
    private WebAccAccountService webAccAccountService;

    /**
     * 查询用户列表
     * @param queryAccAccountVO
     * @return
     */
    @PostMapping("/queryAccountInfoList")
    public CommonResult<List<AccAccountVO>> queryAccountInfoList(@RequestBody QueryAccAccountVO queryAccAccountVO){

        return webAccAccountService.queryAccountInfoList(queryAccAccountVO);
    }

    @GetMapping("/getUserInfo")
    public CommonResult<UserInfoVO> getUserInfo(){

        return webAccAccountService.getUserInfo();
    }
}
