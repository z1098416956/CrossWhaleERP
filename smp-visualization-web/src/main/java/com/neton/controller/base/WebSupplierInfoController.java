package com.neton.controller.base;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.CreateSupplierInfoReqVO;
import com.neton.req.QuerySupplierInfoReqVO;
import com.neton.req.UpdateSupplierInfoReqVO;
import com.neton.res.SupplierInfoDetailsResVO;
import com.neton.service.base.WebSupplierInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/v1/supplier")
public class WebSupplierInfoController {

    @Autowired
    private WebSupplierInfoService webSupplierInfoService;

    /**
     * 创建供应商信息
     * @param createSupplierInfoReqVO
     * @return
     */
    @PostMapping("/saveSupplierInfo")
    public CommonResult<Void> saveSupplierInfo(@RequestBody CreateSupplierInfoReqVO createSupplierInfoReqVO) {

        return  webSupplierInfoService.saveSupplierInfo(createSupplierInfoReqVO);
    }

    /**
     * 更新供应商信息
     * @param updateSupplierInfoReqVO
     * @return
     */
    @PostMapping("/updateSupplierInfo")
    public CommonResult<Void> updateSupplierInfo(@RequestBody UpdateSupplierInfoReqVO updateSupplierInfoReqVO) {
        return webSupplierInfoService.updateSupplierInfo(updateSupplierInfoReqVO);
    }

    /**
     * 删除供应商
     * @param id
     * @return
     */
    @DeleteMapping("/deleteSupplierInfo")
    public CommonResult<Void> deleteSupplierInfo(@RequestParam Long id) {

        return webSupplierInfoService.deleteSupplierInfo(id);
    }

    /**
     * 获取供应商信息
     * @param id
     * @return
     */
    @GetMapping("/getSupplierInfo")
    public CommonResult<SupplierInfoDetailsResVO> getSupplierInfo(@RequestParam Long id) {
        return webSupplierInfoService.getSupplierInfo(id);
    }

    /**
     * 供应商信息
     * @param querySupplierInfoReqVO
     * @return
     */
    @PostMapping("/listSupplierInfo")
    public CommonResult<PageUtil<SupplierInfoDetailsResVO>> listSupplierInfo(@RequestBody QuerySupplierInfoReqVO querySupplierInfoReqVO) {
        return webSupplierInfoService.listSupplierInfo(querySupplierInfoReqVO);
    }
}
