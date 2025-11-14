package com.neton.controller;

import com.neton.service.PurchaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/purchase/info")
public class PurchaseInfoController {
    @Autowired
    private PurchaseInfoService purchaseInfoService;
}
