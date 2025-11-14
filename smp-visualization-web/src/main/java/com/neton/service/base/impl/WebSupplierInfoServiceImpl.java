package com.neton.service.base.impl;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.base.SupplierClient;
import com.neton.req.CreateSupplierInfoReqVO;
import com.neton.req.QuerySupplierInfoReqVO;
import com.neton.req.UpdateSupplierInfoReqVO;
import com.neton.res.SupplierInfoDetailsResVO;
import com.neton.service.base.WebSupplierInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebSupplierInfoServiceImpl implements WebSupplierInfoService {

    @Autowired
    private SupplierClient supplierClient;
    /**
     * 创建供应商信息
     *
     * @param createSupplierInfoReqVO
     * @return
     */
    @Override
    public CommonResult<Void> saveSupplierInfo(CreateSupplierInfoReqVO createSupplierInfoReqVO) {
        return supplierClient.saveSupplierInfo(createSupplierInfoReqVO);
    }

    /**
     * 更新供应商信息
     *
     * @param updateSupplierInfoReqVO
     * @return
     */
    @Override
    public CommonResult<Void> updateSupplierInfo(UpdateSupplierInfoReqVO updateSupplierInfoReqVO) {
        return supplierClient.updateSupplierInfo(updateSupplierInfoReqVO);
    }

    /**
     * 删除供应商
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<Void> deleteSupplierInfo(Long id) {
        return supplierClient.deleteSupplierInfo(id);
    }

    /**
     * 获取供应商信息
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<SupplierInfoDetailsResVO> getSupplierInfo(Long id) {
        return supplierClient.getSupplierInfo(id);
    }

    /**
     * 供应商信息
     *
     * @param querySupplierInfoReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<SupplierInfoDetailsResVO>> listSupplierInfo(QuerySupplierInfoReqVO querySupplierInfoReqVO) {
        return supplierClient.listSupplierInfo(querySupplierInfoReqVO);
    }
}
