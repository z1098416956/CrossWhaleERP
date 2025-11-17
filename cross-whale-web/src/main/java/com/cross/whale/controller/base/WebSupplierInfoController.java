package com.cross.whale.controller.base;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateSupplierInfoReqVO;
import com.cross.whale.req.QuerySupplierInfoReqVO;
import com.cross.whale.req.UpdateSupplierInfoReqVO;
import com.cross.whale.res.SupplierInfoDetailsResVO;
import com.cross.whale.service.base.WebSupplierInfoService;
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
