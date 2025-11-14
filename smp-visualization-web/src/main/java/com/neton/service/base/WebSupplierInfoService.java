package com.neton.service.base;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.CreateSupplierInfoReqVO;
import com.neton.req.QuerySupplierInfoReqVO;
import com.neton.req.UpdateSupplierInfoReqVO;
import com.neton.res.SupplierInfoDetailsResVO;

public interface WebSupplierInfoService {

    /**
     * 创建供应商信息
     * @param createSupplierInfoReqVO
     * @return
     */
    CommonResult<Void> saveSupplierInfo(CreateSupplierInfoReqVO createSupplierInfoReqVO);

    /**
     * 更新供应商信息
     * @param updateSupplierInfoReqVO
     * @return
     */
    CommonResult<Void> updateSupplierInfo(UpdateSupplierInfoReqVO updateSupplierInfoReqVO);

    /**
     * 删除供应商
     * @param id
     * @return
     */
    CommonResult<Void> deleteSupplierInfo(Long id);

    /**
     * 获取供应商信息
     * @param id
     * @return
     */
    CommonResult<SupplierInfoDetailsResVO> getSupplierInfo(Long id);

    /**
     * 供应商信息
     * @param querySupplierInfoReqVO
     * @return
     */
    CommonResult<PageUtil<SupplierInfoDetailsResVO>> listSupplierInfo(QuerySupplierInfoReqVO querySupplierInfoReqVO);
}
