package com.cross.whale.service.purchase.Impl;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.feign.purchase.PurchaseClient;
import com.cross.whale.req.CreatePurchaseInfoReqVO;
import com.cross.whale.req.QueryPurchaseInfoPageReqVO;
import com.cross.whale.req.UpdatePurchaseInfoReqVO;
import com.cross.whale.req.UpdatePurchaseInfoStatusReqVO;
import com.cross.whale.res.PurchaseInfoPageResVO;
import com.cross.whale.res.PurchaseInfoResVO;
import com.cross.whale.service.purchase.WebPurchaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebPurchaseInfoServiceImpl implements WebPurchaseInfoService {

    @Autowired
    private PurchaseClient purchaseClient;
    /**
     * 创建采购单
     *
     * @param createPurchaseInfoReqVO
     * @return
     */
    @Override
    public CommonResult<Void> createPurchaseInfo(CreatePurchaseInfoReqVO createPurchaseInfoReqVO) {
        return purchaseClient.createPurchaseInfo(createPurchaseInfoReqVO);
    }

    /**
     * 更新采购单
     *
     * @param updatePurchaseInfoReqVO
     * @return
     */
    @Override
    public CommonResult<Void> updatePurchaseInfo(UpdatePurchaseInfoReqVO updatePurchaseInfoReqVO) {
        return purchaseClient.updatePurchaseInfo(updatePurchaseInfoReqVO);
    }

    /**
     * 获取采购单详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<PurchaseInfoResVO> getPurchaseInfo(Long id) {
        return purchaseClient.getPurchaseInfo(id);
    }

    /**
     * 删除采购单
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<Void> deletePurchaseInfo(Long id) {
        return purchaseClient.deletePurchaseInfo(id);
    }

    /**
     * 更新采购订单状态
     *
     * @param updatePurchaseInfoStatusReqVO
     * @return
     */
    @Override
    public CommonResult<Void> updatePurchaseInfoStatus(UpdatePurchaseInfoStatusReqVO updatePurchaseInfoStatusReqVO) {
        return purchaseClient.updatePurchaseInfoStatus(updatePurchaseInfoStatusReqVO);
    }

    /**
     * 采购单分页
     *
     * @param queryPurchaseInfoPageReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<PurchaseInfoPageResVO>> queryPurchaseInfoPage(QueryPurchaseInfoPageReqVO queryPurchaseInfoPageReqVO) {
        return purchaseClient.queryPurchaseInfoPage(queryPurchaseInfoPageReqVO);
    }
}
