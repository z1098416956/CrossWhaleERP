package com.cross.whale.service.purchase;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreatePurchaseInfoReqVO;
import com.cross.whale.req.QueryPurchaseInfoPageReqVO;
import com.cross.whale.req.UpdatePurchaseInfoReqVO;
import com.cross.whale.req.UpdatePurchaseInfoStatusReqVO;
import com.cross.whale.res.PurchaseInfoPageResVO;
import com.cross.whale.res.PurchaseInfoResVO;

public interface WebPurchaseInfoService {

    /**
     * 创建采购单
     * @param createPurchaseInfoReqVO
     * @return
     */
    CommonResult<Void> createPurchaseInfo(CreatePurchaseInfoReqVO createPurchaseInfoReqVO);

    /**
     * 更新采购单
     * @param updatePurchaseInfoReqVO
     * @return
     */
    CommonResult<Void> updatePurchaseInfo(UpdatePurchaseInfoReqVO updatePurchaseInfoReqVO);

    /**
     * 获取采购单详情
     * @param id
     * @return
     */
    CommonResult<PurchaseInfoResVO> getPurchaseInfo(Long id);

    /**
     * 删除采购单
     * @param id
     * @return
     */
    CommonResult<Void> deletePurchaseInfo(Long id);

    /**
     * 更新采购订单状态
     * @param updatePurchaseInfoStatusReqVO
     * @return
     */
    CommonResult<Void> updatePurchaseInfoStatus(UpdatePurchaseInfoStatusReqVO updatePurchaseInfoStatusReqVO);

    /**
     * 采购单分页
     * @param queryPurchaseInfoPageReqVO
     * @return
     */
    CommonResult<PageUtil<PurchaseInfoPageResVO>> queryPurchaseInfoPage(QueryPurchaseInfoPageReqVO queryPurchaseInfoPageReqVO);
}
