package com.cross.whale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.dao.PurchaseReceiveDao;
import com.cross.whale.entity.PurchaseReceiveDO;
import com.cross.whale.req.*;
import com.cross.whale.res.PurchaseReceivePageResVO;
import com.cross.whale.service.PurchaseReceiveService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PurchaseReceiveServiceImpl extends ServiceImpl<PurchaseReceiveDao,PurchaseReceiveDO> implements PurchaseReceiveService {
    /**
     * 创建采购入库
     *
     * @param createPurchaseReceiveReqVO
     * @return
     */
    @Override
    public CommonResult<Void> savePurchaseReceive(CreatePurchaseReceiveReqVO createPurchaseReceiveReqVO) {
        if (StringUtils.isNotBlank(createPurchaseReceiveReqVO.getPurchaseNumber())){

        }
        return null;
    }

    /**
     * 更新采购入库信息
     *
     * @param updatePurchaseReceiveReqVO
     * @return
     */
    @Override
    public CommonResult<Void> updatePurchaseReceive(UpdatePurchaseReceiveReqVO updatePurchaseReceiveReqVO) {
        return null;
    }

    /**
     * 删除采购入库
     *
     * @param deletePurchaseReceiveReqVO
     * @return
     */
    @Override
    public CommonResult<Void> deletePurchaseReceive(DeletePurchaseReceiveReqVO deletePurchaseReceiveReqVO) {
        return null;
    }

    /**
     * 更新采购入库状态
     *
     * @param updatePurchaseReceiveStatusReqVO
     * @return
     */
    @Override
    public CommonResult<Void> updatePurchaseReceiveStatus(UpdatePurchaseReceiveStatusReqVO updatePurchaseReceiveStatusReqVO) {
        return null;
    }

    /**
     * @param queryPurchaseReceivePageReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<PurchaseReceivePageResVO>> queryPurchaseReceivePage(QueryPurchaseReceivePageReqVO queryPurchaseReceivePageReqVO) {
        return null;
    }
}
