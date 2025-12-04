package com.cross.whale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.entity.PurchaseReceiveDO;
import com.cross.whale.req.*;
import com.cross.whale.res.PurchaseReceivePageResVO;

public interface PurchaseReceiveService extends IService<PurchaseReceiveDO> {

    /**
     * 创建采购入库
     * @param createPurchaseReceiveReqVO
     * @return
     */
    CommonResult<Void> savePurchaseReceive(CreatePurchaseReceiveReqVO createPurchaseReceiveReqVO);

    /**
     * 更新采购入库信息
     * @param updatePurchaseReceiveReqVO
     * @return
     */
    CommonResult<Void> updatePurchaseReceive(UpdatePurchaseReceiveReqVO updatePurchaseReceiveReqVO);

    /**
     * 删除采购入库
     * @param deletePurchaseReceiveReqVO
     * @return
     */
    CommonResult<Void> deletePurchaseReceive(DeletePurchaseReceiveReqVO deletePurchaseReceiveReqVO);


    /**
     * 更新采购入库状态
     * @param updatePurchaseReceiveStatusReqVO
     * @return
     */
    CommonResult<Void> updatePurchaseReceiveStatus(UpdatePurchaseReceiveStatusReqVO updatePurchaseReceiveStatusReqVO);

    /**
     *
     * @param queryPurchaseReceivePageReqVO
     * @return
     */
    CommonResult<PageUtil<PurchaseReceivePageResVO>> queryPurchaseReceivePage(QueryPurchaseReceivePageReqVO queryPurchaseReceivePageReqVO);
}
