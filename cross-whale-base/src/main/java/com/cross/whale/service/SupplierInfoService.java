package com.cross.whale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.entity.SupplierInfoDO;
import com.cross.whale.req.CreateSupplierInfoReqVO;
import com.cross.whale.req.QuerySupplierInfoReqVO;
import com.cross.whale.req.UpdateSupplierInfoReqVO;
import com.cross.whale.res.SupplierInfoDetailsResVO;

public interface SupplierInfoService extends IService<SupplierInfoDO> {

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
