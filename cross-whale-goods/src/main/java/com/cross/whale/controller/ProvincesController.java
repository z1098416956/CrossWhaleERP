package com.cross.whale.controller;

import com.cross.whale.common.CommonResult;
import com.cross.whale.res.ProvincesVO;
import com.cross.whale.service.ProvincesService;
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
