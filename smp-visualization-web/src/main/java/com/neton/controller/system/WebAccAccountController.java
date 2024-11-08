package com.neton.controller.system;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.CreateAccAccountVO;
import com.neton.req.QueryAccAccountVO;
import com.neton.req.UpdateAccAccountVO;
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

    @PostMapping("/queryUserInfoPage")
    public CommonResult<PageUtil<UserInfoVO>> queryUserInfoPage(@RequestBody QueryAccAccountVO queryAccAccountVO){

        return webAccAccountService.queryUserInfoPage(queryAccAccountVO);
    }

    @PostMapping("/createUserInfo")
    public CommonResult createUserInfo(@RequestBody CreateAccAccountVO createAccAccountVO){

        return webAccAccountService.createUserInfo(createAccAccountVO);
    }

    @GetMapping("/deleteUserById")
    public CommonResult deleteUserById(@RequestParam Long id){

        return webAccAccountService.deleteUserById(id);
    }

    @GetMapping("/getUserInfoById")
    public CommonResult<UserInfoVO> getUserInfoById(@RequestParam Long id){

        return webAccAccountService.getUserInfoById(id);
    }

    /**
     * 更新用户信息
     * @param updateAccAccountVO
     * @return
     */
    @PostMapping("/updateUserInfo")
    public CommonResult updateUserInfo(@RequestBody UpdateAccAccountVO updateAccAccountVO){

        return webAccAccountService.updateUserInfo(updateAccAccountVO);
    }
}
