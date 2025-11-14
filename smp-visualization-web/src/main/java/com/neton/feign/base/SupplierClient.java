package com.neton.feign.base;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.FeignConfig;
import com.neton.req.CreateSupplierInfoReqVO;
import com.neton.req.QuerySupplierInfoReqVO;
import com.neton.req.UpdateSupplierInfoReqVO;
import com.neton.res.SupplierInfoDetailsResVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "smp-base-service" ,configuration = FeignConfig.class,contextId = "base-supplier-service")
public interface SupplierClient {

    /**
     * 创建供应商信息
     * @param createSupplierInfoReqVO
     * @return
     */
    @PostMapping("/saveSupplierInfo")
    public CommonResult<Void> saveSupplierInfo(@RequestBody CreateSupplierInfoReqVO createSupplierInfoReqVO);
    /**
     * 更新供应商信息
     * @param updateSupplierInfoReqVO
     * @return
     */
    @PostMapping("/updateSupplierInfo")
    public CommonResult<Void> updateSupplierInfo(@RequestBody UpdateSupplierInfoReqVO updateSupplierInfoReqVO);

    /**
     * 删除供应商
     * @param id
     * @return
     */
    @DeleteMapping("/deleteSupplierInfo")
    public CommonResult<Void> deleteSupplierInfo(@RequestParam Long id);

    /**
     * 获取供应商信息
     * @param id
     * @return
     */
    @GetMapping("/getSupplierInfo")
    public CommonResult<SupplierInfoDetailsResVO> getSupplierInfo(@RequestParam Long id);

    /**
     * 供应商信息
     * @param querySupplierInfoReqVO
     * @return
     */
    @PostMapping("/listSupplierInfo")
    public CommonResult<PageUtil<SupplierInfoDetailsResVO>> listSupplierInfo(@RequestBody QuerySupplierInfoReqVO querySupplierInfoReqVO);
}
