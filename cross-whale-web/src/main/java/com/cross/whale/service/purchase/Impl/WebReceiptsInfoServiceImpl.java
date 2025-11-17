package com.cross.whale.service.purchase.Impl;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.feign.purchase.PurchaseClient;
import com.cross.whale.req.*;
import com.cross.whale.res.ReceiptsInfoPageResVO;
import com.cross.whale.res.ReceiptsInfoResVO;
import com.cross.whale.service.purchase.WebReceiptsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebReceiptsInfoServiceImpl implements WebReceiptsInfoService {

    @Autowired
    private PurchaseClient purchaseClient;
    /**
     * 添加请购单
     *
     * @param createReceiptsInfoReqVO
     * @return
     */
    @Override
    public CommonResult<Void> createReceiptsInfo(CreateReceiptsInfoReqVO createReceiptsInfoReqVO) {
        return purchaseClient.createReceiptsInfo(createReceiptsInfoReqVO);
    }

    /**
     * 获取请购单详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<ReceiptsInfoResVO> getReceiptsDetailsInfo(Long id) {
        return purchaseClient.getReceiptsDetailsInfo(id);
    }

    /**
     * 删除请购单
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<Void> deleteReceiptsInfo(Long id) {
        return purchaseClient.deleteReceiptsInfo(id);
    }

    /**
     * 更新请购单状态
     *
     * @param updateReceiptsStatusReqVO
     * @return
     */
    @Override
    public CommonResult<Void> updateReceiptsStatus(UpdateReceiptsStatusReqVO updateReceiptsStatusReqVO) {
        return purchaseClient.updateReceiptsStatus(updateReceiptsStatusReqVO);
    }

    /**
     * 删除单据
     *
     * @param deleteReceiptsReqVO
     * @return
     */
    @Override
    public CommonResult<Void> deleteReceipts(DeleteReceiptsReqVO deleteReceiptsReqVO) {
        return purchaseClient.deleteReceipts(deleteReceiptsReqVO);
    }

    /**
     * 请购单列表
     *
     * @param queryReceiptsReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<ReceiptsInfoPageResVO>> queryReceiptsPage(QueryReceiptsReqVO queryReceiptsReqVO) {
        return purchaseClient.queryReceiptsPage(queryReceiptsReqVO);
    }

    /**
     * 更新单据信息
     *
     * @param updateReceiptsInfoReqVO
     * @return
     */
    @Override
    public CommonResult<Void> updateReceiptsInfo(UpdateReceiptsInfoReqVO updateReceiptsInfoReqVO) {
        return purchaseClient.updateReceiptsInfo(updateReceiptsInfoReqVO);
    }
}
