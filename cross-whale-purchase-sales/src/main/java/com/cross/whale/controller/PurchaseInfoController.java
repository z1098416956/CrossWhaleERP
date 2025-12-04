package com.cross.whale.controller;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreatePurchaseInfoReqVO;
import com.cross.whale.req.QueryPurchaseInfoPageReqVO;
import com.cross.whale.req.UpdatePurchaseInfoReqVO;
import com.cross.whale.req.UpdatePurchaseInfoStatusReqVO;
import com.cross.whale.res.PurchaseInfoPageResVO;
import com.cross.whale.res.PurchaseInfoResVO;
import com.cross.whale.service.PurchaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/purchase/info")
public class PurchaseInfoController {
    @Autowired
    private PurchaseInfoService purchaseInfoService;


    /**
     * 创建采购单
     * @param createPurchaseInfoReqVO
     * @return
     */
    @PostMapping("/createPurchaseInfo")
    public CommonResult<Void> createPurchaseInfo(@RequestBody CreatePurchaseInfoReqVO createPurchaseInfoReqVO){

        return purchaseInfoService.createPurchaseInfo(createPurchaseInfoReqVO);
    }

    /**
     * 更新采购单
     * @param updatePurchaseInfoReqVO
     * @return
     */
    @PutMapping("/updatePurchaseInfo")
    public CommonResult<Void> updatePurchaseInfo(@RequestBody UpdatePurchaseInfoReqVO updatePurchaseInfoReqVO){
        return purchaseInfoService.updatePurchaseInfo(updatePurchaseInfoReqVO);
    }

    /**
     * 获取采购单详情
     * @param id
     * @return
     */
    @GetMapping("/getPurchaseInfo")
    public CommonResult<PurchaseInfoResVO> getPurchaseInfo(@RequestParam Long id){
        return purchaseInfoService.getPurchaseInfo(id);
    }

    /**
     * 删除采购单
     * @param id
     * @return
     */
    @DeleteMapping("/deletePurchaseInfo")
    public CommonResult<Void> deletePurchaseInfo(@RequestParam Long id){
        return purchaseInfoService.deletePurchaseInfo(id);
    }

    /**
     * 更新采购订单状态
     * @param updatePurchaseInfoStatusReqVO
     * @return
     */
    @PutMapping("/updatePurchaseInfoStatus")
    public CommonResult<Void> updatePurchaseInfoStatus(@RequestBody UpdatePurchaseInfoStatusReqVO updatePurchaseInfoStatusReqVO){
        return purchaseInfoService.updatePurchaseInfoStatus(updatePurchaseInfoStatusReqVO);
    }

    /**
     * 采购单分页
     * @param queryPurchaseInfoPageReqVO
     * @return
     */
    @PostMapping("/queryPurchaseInfoPage")
    public CommonResult<PageUtil<PurchaseInfoPageResVO>> queryPurchaseInfoPage(@RequestBody QueryPurchaseInfoPageReqVO queryPurchaseInfoPageReqVO){
        return purchaseInfoService.queryPurchaseInfoPage(queryPurchaseInfoPageReqVO);
    }
}
