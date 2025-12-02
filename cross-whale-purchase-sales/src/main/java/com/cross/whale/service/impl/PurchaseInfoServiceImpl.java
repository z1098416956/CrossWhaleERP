package com.cross.whale.service.impl;

import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.ServiceException;
import com.cross.whale.common.SystemErrorCodeConstants;
import com.cross.whale.dao.PurchaseInfoDao;
import com.cross.whale.entity.PurchaseInfoDO;
import com.cross.whale.entity.PurchaseInfoDetailsDO;
import com.cross.whale.req.CreatePurchaseInfoDetailsReqVO;
import com.cross.whale.req.CreatePurchaseInfoReqVO;
import com.cross.whale.req.UpdatePurchaseInfoReqVO;
import com.cross.whale.req.UpdatePurchaseInfoStatusReqVO;
import com.cross.whale.res.PurchaseInfoResVO;
import com.cross.whale.res.ReceiptsInfoDetailsResVO;
import com.cross.whale.res.ReceiptsInfoResVO;
import com.cross.whale.service.PurchaseInfoDetailsService;
import com.cross.whale.service.PurchaseInfoService;
import com.cross.whale.service.ReceiptsInfoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.cross.whale.utils.PurchaseSalesStatusConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseInfoServiceImpl extends ServiceImpl<PurchaseInfoDao, PurchaseInfoDO> implements PurchaseInfoService {

    @Autowired
    private ReceiptsInfoService receiptsInfoService;

    @Autowired
    private PurchaseInfoDetailsService purchaseInfoDetailsService;

    /**
     * 创建采购单
     *
     * @param createPurchaseInfoReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> createPurchaseInfo(CreatePurchaseInfoReqVO createPurchaseInfoReqVO) {
        if (createPurchaseInfoReqVO.getPurchaseStatus() >= 2){
            return CommonResult.error(SystemErrorCodeConstants.RECEIPTS_SETTLEMENT_STATUS_IS_ERR);
        }
        if (StringUtils.isNoneBlank(createPurchaseInfoReqVO.getPurchaseNumber())) {
            String purchaseNumber = createPurchaseInfoReqVO.getPurchaseNumber();
            //查询请购单商品信息
            ReceiptsInfoResVO resVO = receiptsInfoService.getPurchaseInfoByPurchaseNumber(purchaseNumber);
            if (resVO == null) {
                return CommonResult.error(SystemErrorCodeConstants.RECEIPTS_PURCHASE_NUMBER_IS_NOT_EXIST);
            }
            if (resVO.getReceiptsStatus() != 1 || resVO.getReceiptsStatus() != 4) {
                //请购单状态不是待采购或待审核，不能创建采购单
                return CommonResult.error(SystemErrorCodeConstants.RECEIPTS_PURCHASE_NUMBER_IS_NOT_PURCHASE);
            }
            //对比采购单提交的请购单里面的商品是否一致
            List<ReceiptsInfoDetailsResVO> receiptsInfoDetails = resVO.getReceiptsInfoDetails();
            if (receiptsInfoDetails == null || receiptsInfoDetails.isEmpty()) {
                throw new ServiceException(SystemErrorCodeConstants.RECEIPTS_PURCHASE_NUMBER_IS_EMPTY);
            }
            //对比采购单提交的请购单里面的商品是否一致
            List<CreatePurchaseInfoDetailsReqVO> details = createPurchaseInfoReqVO.getDetails();
            if (details == null || details.isEmpty()) {
                throw new ServiceException(SystemErrorCodeConstants.RECEIPTS_PURCHASE_NUMBER_IS_EMPTY);
            }
            for (CreatePurchaseInfoDetailsReqVO purchaseInfoDetail : details) {
                 boolean isExist = false;
                 for (ReceiptsInfoDetailsResVO receiptsInfoDetail : receiptsInfoDetails) {
                     if (receiptsInfoDetail.getGoodsId().longValue() == purchaseInfoDetail.getGoodsId().longValue()) {
                         isExist = true;
                         break;
                     }
                 }
                if (!isExist) {
                    //请购单里面的商品不在采购单里面
                    throw new ServiceException(SystemErrorCodeConstants.RECEIPTS_PURCHASE_NUMBER_IS_ERROR);
                }
            }
            //更新请购单信息
            if ( receiptsInfoDetails.size() == details.size()){
                Map<String, Integer> goodsRemaining = checkPurchaseInfoByReceiptsNumber(purchaseNumber, details,receiptsInfoDetails);

            }else {
                receiptsInfoService.updateReceiptsStatusById(resVO.getId(), receiptsInfoDetails.size() == details.size() ?
                        PurchaseSalesStatusConstants.RequisitionStatus.PURCHASE_COMPLETED.getCode() :
                        PurchaseSalesStatusConstants.RequisitionStatus.PARTIALLY_PURCHASE_COMPLETED.getCode());
            }

        }


        return null;
    }


    /**
     * 根据请购编号，查询是否有采购订单，并校验是否满足当前采购要求
     */
    private Map<String,Integer> checkPurchaseInfoByReceiptsNumber(String receiptsNumber,List<CreatePurchaseInfoDetailsReqVO> details,List<ReceiptsInfoDetailsResVO> receiptsInfoDetails) {
        List<PurchaseInfoDO> purchaseInfoDOList = baseMapper.selectByReceiptsNumber(receiptsNumber);
        Map<String,Integer> goodsRemaining = new HashMap<>();
        if (purchaseInfoDOList == null || purchaseInfoDOList.isEmpty()) {
           return goodsRemaining;
        }
        //获取采购单ID获取采购单信息
        List<Long> list = purchaseInfoDOList.stream().map(PurchaseInfoDO::getId).toList();
        List<PurchaseInfoDetailsDO> purchaseInfoDetailsDOList = purchaseInfoDetailsService.listByPurchaseInfoIds(list);
        if (purchaseInfoDetailsDOList == null || purchaseInfoDetailsDOList.isEmpty()) {
            return goodsRemaining;
        }
        //根据SKU分组
        Map<String, List<ReceiptsInfoDetailsResVO>> receiptsMap = receiptsInfoDetails.stream().collect(Collectors.groupingBy(ReceiptsInfoDetailsResVO::getGoodsSku));
        Map<String, List<PurchaseInfoDetailsDO>> collect = purchaseInfoDetailsDOList.stream().collect(Collectors.groupingBy(PurchaseInfoDetailsDO::getGoodsSku));
        Map<String,Integer> goodsSum = new HashMap<>();
        for (String goodsSku : collect.keySet()) {
            collect.get(goodsSku).forEach(detail -> {
                Integer sum = goodsSum.get(goodsSku);
                if (sum == null) {
                    goodsSum.put(goodsSku, detail.getPurchasedNumber() == null ? 0 : detail.getPurchasedNumber());
                }else {
                    int i = detail.getPurchasedNumber() == null ? 0 : detail.getPurchasedNumber();
                    sum += i;
                    goodsSum.put(goodsSku, sum);
                }
            });
        }
        //对比采购单提交的请购单里面的商品是否一致

        for (CreatePurchaseInfoDetailsReqVO purchaseInfoDetail : details) {
            if (purchaseInfoDetail.getPurchasedNumber() > purchaseInfoDetail.getQuantity()) {
                //采购单里面的商品数量不足
                throw new ServiceException(SystemErrorCodeConstants.RECEIPTS_PURCHASE_NUMBER_QUANTITY_ERROR);
            }
            Integer sum = goodsSum.get(purchaseInfoDetail.getGoodsId()) == null ? 0 : goodsSum.get(purchaseInfoDetail.getGoodsId());
            int temp = purchaseInfoDetail.getQuantity() - sum;
            if (temp < purchaseInfoDetail.getPurchasedNumber()) {
                //采购单里面的商品数量不足
                throw new ServiceException(SystemErrorCodeConstants.RECEIPTS_PURCHASE_NUMBER_QUANTITY_ERROR);
            }
            //放入剩余采购数量
            goodsRemaining.put(purchaseInfoDetail.getGoodsSku(), temp - purchaseInfoDetail.getPurchasedNumber());
        }

        return goodsRemaining;
    }

    /**
     * 更新采购单
     *
     * @param updatePurchaseInfoReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> updatePurchaseInfoStatus(UpdatePurchaseInfoStatusReqVO updatePurchaseInfoStatusReqVO) {
        return null;
    }
}
