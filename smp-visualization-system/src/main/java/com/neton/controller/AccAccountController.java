package com.neton.controller;

import com.neton.common.CommonResult;
import com.neton.req.QueryAccAccountVO;
import com.neton.res.AccAccountVO;
import com.neton.service.AccAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public CommonResult<List<AccAccountVO>> queryAccountInfoList(@RequestBody QueryAccAccountVO queryAccAccountVO){

        return accAccountService.queryAccountInfoList(queryAccAccountVO);
    }
}
