package com.neton.controller;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.CreateSupplierInfoReqVO;
import com.neton.req.QuerySupplierInfoReqVO;
import com.neton.req.UpdateSupplierInfoReqVO;
import com.neton.res.SupplierInfoDetailsResVO;
import com.neton.service.SupplierInfoService;

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
@RequestMapping("/v1/supplier/info")
public class SupplierInfoController {

    @Autowired
    private SupplierInfoService supplierInfoService;

    /**
     * 创建供应商信息
     * @param createSupplierInfoReqVO
     * @return
     */
    @PostMapping("/saveSupplierInfo")
    public CommonResult<Void> saveSupplierInfo(@RequestBody CreateSupplierInfoReqVO createSupplierInfoReqVO) {
       
        return  supplierInfoService.saveSupplierInfo(createSupplierInfoReqVO);
    }

    /**
     * 更新供应商信息
     * @param updateSupplierInfoReqVO
     * @return
     */
    @PutMapping("/updateSupplierInfo")
    public CommonResult<Void> updateSupplierInfo(@RequestBody UpdateSupplierInfoReqVO updateSupplierInfoReqVO) {
        return supplierInfoService.updateSupplierInfo(updateSupplierInfoReqVO);
    }

    /**
     * 删除供应商
     * @param id
     * @return
     */
    @DeleteMapping("/deleteSupplierInfo")
    public CommonResult<Void> deleteSupplierInfo(@RequestParam Long id) {
        
        return supplierInfoService.deleteSupplierInfo(id);
    }

    /**
     * 获取供应商信息
     * @param id
     * @return
     */
    @GetMapping("/getSupplierInfo")
    public CommonResult<SupplierInfoDetailsResVO> getSupplierInfo(@RequestParam Long id) {
        return supplierInfoService.getSupplierInfo(id);
    }

    /**
     * 供应商信息
     * @param querySupplierInfoReqVO
     * @return
     */
    @PostMapping("/listSupplierInfo")
    public CommonResult<PageUtil<SupplierInfoDetailsResVO>> listSupplierInfo(@RequestBody QuerySupplierInfoReqVO querySupplierInfoReqVO) {
        return supplierInfoService.listSupplierInfo(querySupplierInfoReqVO);
    }
}
