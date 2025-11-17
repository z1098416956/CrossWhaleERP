package com.cross.whale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.SystemErrorCodeConstants;
import com.cross.whale.dao.PurchaseInfoDao;
import com.cross.whale.entity.PurchaseInfoDO;
import com.cross.whale.req.CreatePurchaseInfoReqVO;
import com.cross.whale.req.UpdatePurchaseInfoReqVO;
import com.cross.whale.req.UpdatePurchaseInfoStatusReqVO;
import com.cross.whale.res.PurchaseInfoResVO;
import com.cross.whale.service.PurchaseInfoService;
import org.springframework.stereotype.Service;

@Service
public class PurchaseInfoServiceImpl extends ServiceImpl<PurchaseInfoDao, PurchaseInfoDO> implements PurchaseInfoService {
    /**
     * 创建采购单
     *
     * @param createPurchaseInfoReqVO
     * @return
     */
    @Override
    public CommonResult<Void> createPurchaseInfo(CreatePurchaseInfoReqVO createPurchaseInfoReqVO) {
        if (createPurchaseInfoReqVO.getPurchaseStatus() >= 2){
            return CommonResult.error(SystemErrorCodeConstants.RECEIPTS_SETTLEMENT_STATUS_IS_ERR);
        }

        return null;
    }

    /**
     * 更新采购单
     *
     * @param updatePurchaseInfoReqVO
     * @return
     */
    @Override
    public CommonResult<Void> updatePurchaseInfo(UpdatePurchaseInfoReqVO updatePurchaseInfoReqVO) {
        return null;
    }

    /**
     * 获取采购单详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<PurchaseInfoResVO> getPurchaseInfo(Long id) {
        return null;
    }

    /**
     * 删除采购单
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<Void> deletePurchaseInfo(Long id) {
        return null;
    }

    /**
     * 更新采购订单状态
     *
     * @param updatePurchaseInfoStatusReqVO
     * @return
     */
    @Override
    public CommonResult<Void> updatePurchaseInfoStatus(UpdatePurchaseInfoStatusReqVO updatePurchaseInfoStatusReqVO) {
        return null;
    }
}
