package com.cross.whale.feign;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateSupplierInfoReqVO;
import com.cross.whale.req.QuerySupplierInfoReqVO;
import com.cross.whale.req.UpdateSupplierInfoReqVO;
import com.cross.whale.res.SupplierInfoDetailsResVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cross-whale-base" ,configuration = FeignConfig.class,contextId = "base-supplier-service")
public interface SupplierClient {


    /**
     * 获取供应商信息
     * @param id
     * @return
     */
    @GetMapping("/v1/supplier/info/getSupplierInfo")
    public CommonResult<SupplierInfoDetailsResVO> getSupplierInfo(@RequestParam Long id);

    /**
     * 供应商信息
     * @param querySupplierInfoReqVO
     * @return
     */
    @PostMapping("/v1/supplier/info/listSupplierInfo")
    public CommonResult<PageUtil<SupplierInfoDetailsResVO>> listSupplierInfo(@RequestBody QuerySupplierInfoReqVO querySupplierInfoReqVO);
}
