package com.cross.whale.controller;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateAccAccountVO;
import com.cross.whale.req.QueryAccAccountVO;
import com.cross.whale.req.UpdateAccAccountVO;
import com.cross.whale.res.AccAccountVO;
import com.cross.whale.res.UserInfoVO;
import com.cross.whale.service.AccAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author TheSunshine
 * @date 2024-10-31 14:54:27
 */
@RestController
@RequestMapping("/v1/account")
public class AccAccountController {

    @Autowired
    private AccAccountService accAccountService;

    /**
     * 查询用户列表
     * @param queryAccAccountVO
     * @return
     */
    @PostMapping("/queryAccountInfoList")
    public CommonResult<List<AccAccountVO>> queryAccountInfoList(@RequestBody QueryAccAccountVO queryAccAccountVO) {

        return accAccountService.queryAccountInfoList(queryAccAccountVO);
    }

    @GetMapping("/getUserInfo")
    public CommonResult<UserInfoVO> getUserInfo(){

        return accAccountService.getUserInfo();
    }

    @PostMapping("/queryUserInfoPage")
    public CommonResult<PageUtil<UserInfoVO>> queryUserInfoPage(@RequestBody QueryAccAccountVO queryAccAccountVO){

        return accAccountService.queryUserInfoPage(queryAccAccountVO);
    }

    @PostMapping("/createUserInfo")
    public CommonResult createUserInfo(@RequestBody CreateAccAccountVO createAccAccountVO){

        return accAccountService.createUserInfo(createAccAccountVO);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @GetMapping("/deleteUserById")
    public CommonResult deleteUserById(@RequestParam Long id){

        return accAccountService.deleteUserById(id);
    }

    @GetMapping("/getUserInfoById")
    public CommonResult<UserInfoVO> getUserInfoById(@RequestParam Long id){

        return accAccountService.getUserInfoById(id);
    }

    @PostMapping("/updateUserInfo")
    public CommonResult updateUserInfo(@RequestBody UpdateAccAccountVO updateAccAccountVO){

        return accAccountService.updateUserInfo(updateAccAccountVO);
    }
}
