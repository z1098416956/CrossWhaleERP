package com.neton.controller;

import com.neton.common.CommonResult;
import com.neton.res.ProvincesVO;
import com.neton.service.ProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/provinces")
public class ProvincesController {

    @Autowired
    private ProvincesService provincesService;

    @GetMapping("/getProvincesInfo")
    public CommonResult<List<ProvincesVO>> getProvincesInfo(){

        return provincesService.getProvincesInfo();
    }
}
