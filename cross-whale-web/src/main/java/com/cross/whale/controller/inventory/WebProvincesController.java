package com.cross.whale.controller.inventory;

import com.cross.whale.common.CommonResult;
import com.cross.whale.res.ProvincesVO;
import com.cross.whale.service.inventory.WebProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/web/v1/provinces")
public class WebProvincesController {

    @Autowired
    private WebProvincesService provincesService;

    @GetMapping("/getProvincesInfo")
    public CommonResult<List<ProvincesVO>> getProvincesInfo(){

        return provincesService.getProvincesInfo();
    }
}
