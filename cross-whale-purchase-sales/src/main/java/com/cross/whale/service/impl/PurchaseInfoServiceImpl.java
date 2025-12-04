package com.cross.whale.service.impl;

import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cross.whale.bean.NetonBeanUtils;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.common.ServiceException;
import com.cross.whale.common.SystemErrorCodeConstants;
import com.cross.whale.dao.PurchaseInfoDao;
import com.cross.whale.entity.PurchaseInfoDO;
import com.cross.whale.entity.PurchaseInfoDetailsDO;
import com.cross.whale.entity.ReceiptsInfoDO;
import com.cross.whale.entity.ReceiptsInfoDetailsDO;
import com.cross.whale.feign.SupplierClient;
import com.cross.whale.req.*;
import com.cross.whale.res.*;
import com.cross.whale.rocketmq.producer.RocketMQProducer;
import com.cross.whale.service.PurchaseInfoDetailsService;
import com.cross.whale.service.PurchaseInfoService;
import com.cross.whale.service.ReceiptsInfoService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.cross.whale.utils.PurchaseSalesRocketMQConstants;
import com.cross.whale.utils.PurchaseSalesStatusConstants;
import com.cross.whale.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseInfoServiceImpl extends ServiceImpl<PurchaseInfoDao, PurchaseInfoDO> implements PurchaseInfoService {

    @Autowired
    private ReceiptsInfoService receiptsInfoService;

    @Autowired
    private PurchaseInfoDetailsService purchaseInfoDetailsService;

    @Autowired
    private RocketMQProducer rocketMQProducer;

    @Autowired
    private SupplierClient supplierClient;
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
        if (createPurchaseInfoReqVO.getDetails() == null || createPurchaseInfoReqVO.getDetails().isEmpty()) {
            return CommonResult.error(SystemErrorCodeConstants.PURCHASE_INFO_DETAILS_IS_EMPTY);
        }
        // 如果关联了请购单，需要验证请购单状态和商品信息
        if (StringUtils.isNoneBlank(createPurchaseInfoReqVO.getReceiptsNumber())) {
            String receiptsNumber = createPurchaseInfoReqVO.getReceiptsNumber();
            //查询请购单商品信息
            ReceiptsInfoResVO resVO = receiptsInfoService.getPurchaseInfoByPurchaseNumber(receiptsNumber);
            if (resVO == null) {
                return CommonResult.error(SystemErrorCodeConstants.RECEIPTS_PURCHASE_NUMBER_IS_NOT_EXIST);
            }
            if (resVO.getReceiptsStatus() != 1 && resVO.getReceiptsStatus() != 4) {
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

            // 验证采购单中的商品是否都在请购单中
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

            // 检查请购单商品的采购情况，计算剩余可采购数量
            Map<Long, Integer> goodsRemaining = checkPurchaseInfoByReceiptsNumber(receiptsNumber, receiptsInfoDetails);

            // 验证采购数量是否超过剩余可采购数量
            for (CreatePurchaseInfoDetailsReqVO purchaseInfoDetail : details) {

                Integer remainingQuantity = goodsRemaining.get(purchaseInfoDetail.getReceiptsDetailsId());
                if (remainingQuantity != null && purchaseInfoDetail.getPurchasedNumber() > remainingQuantity) {
                    // 采购数量超过剩余可采购数量
                    throw new ServiceException(SystemErrorCodeConstants.RECEIPTS_PURCHASE_NUMBER_QUANTITY_ERROR);
                }
            }

            // 设置请购单ID
            createPurchaseInfoReqVO.setReceiptsId(resVO.getId());
        }

        // 创建采购单
        PurchaseInfoDO purchaseInfoDO = new PurchaseInfoDO();
        // 设置基本信息
        purchaseInfoDO.setReceiptsId(createPurchaseInfoReqVO.getReceiptsId());
        purchaseInfoDO.setReceiptsNumber(createPurchaseInfoReqVO.getReceiptsNumber());
        if (createPurchaseInfoReqVO.getSupplierId() != null) {
            CommonResult<SupplierInfoDetailsResVO> supplierInfo = supplierClient.getSupplierInfo(createPurchaseInfoReqVO.getSupplierId());
            if (supplierInfo.getCode() != 0){
                throw new ServiceException(supplierInfo.getCode(),supplierInfo.getMessage());
            }
            SupplierInfoDetailsResVO data = supplierInfo.getData();
            purchaseInfoDO.setSupplierName(data.getSupplierName());
        }
        purchaseInfoDO.setSupplierId(createPurchaseInfoReqVO.getSupplierId());
        purchaseInfoDO.setWarehouseId(createPurchaseInfoReqVO.getWarehouseId());
        purchaseInfoDO.setDeliveryDate(createPurchaseInfoReqVO.getDeliveryDate());
        purchaseInfoDO.setReceiptsTime(createPurchaseInfoReqVO.getReceiptsTime());
        purchaseInfoDO.setPurchaseNumber(createPurchaseInfoReqVO.getPurchaseNumber());
        purchaseInfoDO.setDiscountRate(createPurchaseInfoReqVO.getDiscountRate());
        purchaseInfoDO.setDiscountsPrice(createPurchaseInfoReqVO.getDiscountsPrice());
        purchaseInfoDO.setPaymentDiscount(createPurchaseInfoReqVO.getPaymentDiscount());
        purchaseInfoDO.setPaymentAccountId(createPurchaseInfoReqVO.getPaymentAccountId());
        purchaseInfoDO.setPaymentAccountName(createPurchaseInfoReqVO.getPaymentAccountName());
        purchaseInfoDO.setFileUrl(createPurchaseInfoReqVO.getFileUrl());
        purchaseInfoDO.setRemake(createPurchaseInfoReqVO.getRemake());
        purchaseInfoDO.setPurchaseStatus(createPurchaseInfoReqVO.getPurchaseStatus());
        purchaseInfoDO.setCreateBy(SecurityUtils.getUserId());
        purchaseInfoDO.setUpdateBy(SecurityUtils.getUserId());
        purchaseInfoDO.setCreateByName(SecurityUtils.getUsername());
        purchaseInfoDO.setUpdateByName(SecurityUtils.getUsername());
        purchaseInfoDO.setUpdateTime(LocalDateTime.now());
        purchaseInfoDO.setCreateTime(LocalDateTime.now());
        // 计算商品数量和商品信息
        if (createPurchaseInfoReqVO.getDetails() != null && !createPurchaseInfoReqVO.getDetails().isEmpty()) {
            purchaseInfoDO.setGoodsQuantity(createPurchaseInfoReqVO.getDetails().size());
            // 这里可以设置商品信息的汇总，比如商品名称列表等
            StringBuilder goodsInfo = new StringBuilder();
            for (int i = 0; i < createPurchaseInfoReqVO.getDetails().size(); i++) {
                CreatePurchaseInfoDetailsReqVO createPurchaseInfoDetailsReqVO = createPurchaseInfoReqVO.getDetails().get(i);
                goodsInfo.append(createPurchaseInfoDetailsReqVO.getGoodsName());
                goodsInfo.append(" ");
                goodsInfo.append(createPurchaseInfoDetailsReqVO.getSpecification());
                goodsInfo.append(" ");
                goodsInfo.append(createPurchaseInfoDetailsReqVO.getModel());
                if (i != createPurchaseInfoReqVO.getDetails().size() - 1) {
                    goodsInfo.append(",");
                }
            }
            purchaseInfoDO.setGoodsInfo(goodsInfo.toString());
        }

        // 保存采购单
        baseMapper.insert(purchaseInfoDO);

        // 保存采购单详情
        List<PurchaseInfoDetailsDO> purchaseInfoDetailsDOList = new ArrayList<>();
        for (CreatePurchaseInfoDetailsReqVO detail : createPurchaseInfoReqVO.getDetails()) {
            PurchaseInfoDetailsDO purchaseInfoDetailsDO = new PurchaseInfoDetailsDO();
            purchaseInfoDetailsDO.setPurchaseId(purchaseInfoDO.getId());
            purchaseInfoDetailsDO.setGoodsId(detail.getGoodsId());
            purchaseInfoDetailsDO.setGoodsAttributeId(detail.getGoodsAttributeId());
            purchaseInfoDetailsDO.setUnitId(detail.getUnitId());
            purchaseInfoDetailsDO.setGoodsBarcode(detail.getGoodsBarcode());
            purchaseInfoDetailsDO.setGoodsName(detail.getGoodsName());
            purchaseInfoDetailsDO.setSpecification(detail.getSpecification());
            purchaseInfoDetailsDO.setQuantity(detail.getQuantity());
            purchaseInfoDetailsDO.setPurchasedNumber(detail.getPurchasedNumber());
            purchaseInfoDetailsDO.setModel(detail.getModel());
            purchaseInfoDetailsDO.setColour(detail.getColour());
            purchaseInfoDetailsDO.setBrandName(detail.getBrandName());
            purchaseInfoDetailsDO.setManufacturer(detail.getManufacturer());
            purchaseInfoDetailsDO.setUnitName(detail.getUnitName());
            purchaseInfoDetailsDO.setGoodsSku(detail.getGoodsSku());
            purchaseInfoDetailsDO.setOthersInfo(detail.getOthersInfo());
            purchaseInfoDetailsDO.setPurchasePrice(detail.getPurchasePrice());
            purchaseInfoDetailsDO.setRemake(detail.getRemake());
            purchaseInfoDetailsDO.setTotalPrice(detail.getTotalPrice());
            purchaseInfoDetailsDO.setTaxRate(detail.getTaxRate());
            purchaseInfoDetailsDO.setTaxAmount(detail.getTaxAmount());
            purchaseInfoDetailsDO.setTaxPrice(detail.getTaxPrice());

            purchaseInfoDetailsDOList.add(purchaseInfoDetailsDO);
        }

        // 批量保存采购单详情
        purchaseInfoDetailsService.saveBatch(purchaseInfoDetailsDOList);
        // 如果关联了请购单，需要更新请购单状态
        try {
            rocketMQProducer.sendMessageAsync(PurchaseSalesRocketMQConstants.RECEIPTS_STATUS_TOPIC, PurchaseSalesRocketMQConstants.RECEIPTS_STATUS_TAG, createPurchaseInfoReqVO.getPurchaseNumber(), new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    String msgId = sendResult.getMsgId();
                    log.debug("消息发送成功 ===============> "+msgId);
                }

                @Override
                public void onException(Throwable throwable) {
                    log.error("消息发送失败 ===============> ｛｝",throwable);
                }
            });
        }catch (Exception e) {
            log.error("更新请购单状态失败 ===============> ｛｝",e);
        }
        return CommonResult.success();
    }


    /**
     * 根据请购编号，查询是否有采购订单，并校验是否满足当前采购要求
     */
    private Map<Long,Integer> checkPurchaseInfoByReceiptsNumber(String receiptsNumber, List<ReceiptsInfoDetailsResVO> receiptsInfoDetails) {
        List<PurchaseInfoDO> purchaseInfoDOList = baseMapper.selectByReceiptsNumber(receiptsNumber);
        Map<Long,Integer> goodsRemaining = new HashMap<>();
        if (purchaseInfoDOList == null || purchaseInfoDOList.isEmpty()) {
            // 如果没有关联的采购单，则所有商品都可以采购
            for (ReceiptsInfoDetailsResVO receiptsInfoDetail : receiptsInfoDetails) {

                goodsRemaining.put(receiptsInfoDetail.getId(), receiptsInfoDetail.getQuantity());
            }
            return goodsRemaining;
        }

        //获取采购单ID获取采购单信息
        List<Long> list = purchaseInfoDOList.stream().map(PurchaseInfoDO::getId).toList();
        List<PurchaseInfoDetailsDO> purchaseInfoDetailsDOList = purchaseInfoDetailsService.listByPurchaseInfoIds(list);
        if (purchaseInfoDetailsDOList == null || purchaseInfoDetailsDOList.isEmpty()) {
            // 如果没有采购单详情，则所有商品都可以采购
            for (ReceiptsInfoDetailsResVO receiptsInfoDetail : receiptsInfoDetails) {

                goodsRemaining.put(receiptsInfoDetail.getId(), receiptsInfoDetail.getQuantity());
            }
            return goodsRemaining;
        }

        //根据请购单详情ID分组统计已采购数量
        Map<Long, Integer> purchasedQuantityMap = new HashMap<>();
        for (PurchaseInfoDetailsDO detail : purchaseInfoDetailsDOList) {
            Integer purchasedQuantity = purchasedQuantityMap.get(detail.getId());
            if (purchasedQuantity == null) {
                purchasedQuantityMap.put(detail.getReceiptsDetailsId(), detail.getPurchasedNumber() == null ? 0 : detail.getPurchasedNumber());
            } else {
                purchasedQuantityMap.put(detail.getReceiptsDetailsId(), purchasedQuantity + (detail.getPurchasedNumber() == null ? 0 : detail.getPurchasedNumber()));
            }
        }

        // 计算剩余可采购数量
        for (ReceiptsInfoDetailsResVO receiptsInfoDetail : receiptsInfoDetails) {
            Integer totalQuantity = receiptsInfoDetail.getQuantity();
            Integer purchasedQuantity = purchasedQuantityMap.get(receiptsInfoDetail.getId()) == null ? 0 : purchasedQuantityMap.get(receiptsInfoDetail.getId());
            Integer remainingQuantity = totalQuantity - purchasedQuantity;
            goodsRemaining.put(receiptsInfoDetail.getId(), remainingQuantity > 0 ? remainingQuantity : 0);
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
        if (updatePurchaseInfoReqVO == null) {
            return CommonResult.error(SystemErrorCodeConstants.PURCHASE_INFO_NULL_ERROR);
        }
        if (updatePurchaseInfoReqVO.getId() == null) {
            return CommonResult.error(SystemErrorCodeConstants.PURCHASE_INFO_ID_IS_NULL);
        }
        if (updatePurchaseInfoReqVO.getDetails() == null || updatePurchaseInfoReqVO.getDetails().isEmpty()) {
            return CommonResult.error(SystemErrorCodeConstants.PURCHASE_INFO_DETAILS_IS_EMPTY);
        }
        PurchaseInfoDO purchaseInfoDO = baseMapper.selectById(updatePurchaseInfoReqVO.getId());
        if (purchaseInfoDO == null) {
            return CommonResult.error(SystemErrorCodeConstants.PURCHASE_INFO_ID_IS_NULL);
        }
        if (purchaseInfoDO.getPurchaseStatus() != PurchaseSalesStatusConstants.PurchaseOrderStatus.NOT_REVIEWED.getCode()) {
            return CommonResult.error(SystemErrorCodeConstants.PURCHASE_INFO_STATUS_IS_ERR);
        }
        // 删除这笔采购单详情
        purchaseInfoDetailsService.deleteByPurchaseInfoId(updatePurchaseInfoReqVO.getId());
        // 如果关联了请购单，需要验证请购单状态和商品信息
        if (StringUtils.isNoneBlank(updatePurchaseInfoReqVO.getReceiptsNumber())) {
            String receiptsNumber = updatePurchaseInfoReqVO.getReceiptsNumber();
            //查询请购单商品信息
            ReceiptsInfoResVO resVO = receiptsInfoService.getPurchaseInfoByPurchaseNumber(receiptsNumber);
            if (resVO == null) {
                return CommonResult.error(SystemErrorCodeConstants.RECEIPTS_PURCHASE_NUMBER_IS_NOT_EXIST);
            }
            if (resVO.getReceiptsStatus() != 1 && resVO.getReceiptsStatus() != 4) {
                //请购单状态不是待采购或待审核，不能创建采购单
                return CommonResult.error(SystemErrorCodeConstants.RECEIPTS_PURCHASE_NUMBER_IS_NOT_PURCHASE);
            }

            //对比采购单提交的请购单里面的商品是否一致
            List<ReceiptsInfoDetailsResVO> receiptsInfoDetails = resVO.getReceiptsInfoDetails();
            if (receiptsInfoDetails == null || receiptsInfoDetails.isEmpty()) {
                throw new ServiceException(SystemErrorCodeConstants.RECEIPTS_PURCHASE_NUMBER_IS_EMPTY);
            }

            //对比采购单提交的请购单里面的商品是否一致
            List<UpdatePurchaseInfoDetailsReqVO> details = updatePurchaseInfoReqVO.getDetails();
            if (details == null || details.isEmpty()) {
                throw new ServiceException(SystemErrorCodeConstants.RECEIPTS_PURCHASE_NUMBER_IS_EMPTY);
            }

            // 验证采购单中的商品是否都在请购单中
            for (UpdatePurchaseInfoDetailsReqVO purchaseInfoDetail : details) {
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

            // 检查请购单商品的采购情况，计算剩余可采购数量
            Map<Long, Integer> goodsRemaining = checkPurchaseInfoByReceiptsNumber(receiptsNumber, receiptsInfoDetails);

            // 验证采购数量是否超过剩余可采购数量
            for (UpdatePurchaseInfoDetailsReqVO purchaseInfoDetail : details) {

                Integer remainingQuantity = goodsRemaining.get(purchaseInfoDetail.getReceiptsDetailsId());
                if (remainingQuantity != null && purchaseInfoDetail.getPurchasedNumber() > remainingQuantity) {
                    // 采购数量超过剩余可采购数量
                    throw new ServiceException(SystemErrorCodeConstants.RECEIPTS_PURCHASE_NUMBER_QUANTITY_ERROR);
                }
            }

            // 设置请购单ID
            updatePurchaseInfoReqVO.setReceiptsId(resVO.getId());
        }
        // 设置基本信息
        purchaseInfoDO.setReceiptsId(updatePurchaseInfoReqVO.getReceiptsId());
        purchaseInfoDO.setReceiptsNumber(updatePurchaseInfoReqVO.getReceiptsNumber());
        if (updatePurchaseInfoReqVO.getSupplierId() != null) {
            CommonResult<SupplierInfoDetailsResVO> supplierInfo = supplierClient.getSupplierInfo(updatePurchaseInfoReqVO.getSupplierId());
            if (supplierInfo.getCode() != 0){
                throw new ServiceException(supplierInfo.getCode(),supplierInfo.getMessage());
            }
            SupplierInfoDetailsResVO data = supplierInfo.getData();
            purchaseInfoDO.setSupplierName(data.getSupplierName());
        }
        purchaseInfoDO.setSupplierId(updatePurchaseInfoReqVO.getSupplierId());
        purchaseInfoDO.setWarehouseId(updatePurchaseInfoReqVO.getWarehouseId());
        purchaseInfoDO.setDeliveryDate(updatePurchaseInfoReqVO.getDeliveryDate());
        purchaseInfoDO.setReceiptsTime(updatePurchaseInfoReqVO.getReceiptsTime());
        purchaseInfoDO.setPurchaseNumber(updatePurchaseInfoReqVO.getPurchaseNumber());
        purchaseInfoDO.setDiscountRate(updatePurchaseInfoReqVO.getDiscountRate());
        purchaseInfoDO.setDiscountsPrice(updatePurchaseInfoReqVO.getDiscountsPrice());
        purchaseInfoDO.setPaymentDiscount(updatePurchaseInfoReqVO.getPaymentDiscount());
        purchaseInfoDO.setPaymentAccountId(updatePurchaseInfoReqVO.getPaymentAccountId());
        purchaseInfoDO.setPaymentAccountName(updatePurchaseInfoReqVO.getPaymentAccountName());
        purchaseInfoDO.setFileUrl(updatePurchaseInfoReqVO.getFileUrl());
        purchaseInfoDO.setRemake(updatePurchaseInfoReqVO.getRemake());
        purchaseInfoDO.setPurchaseStatus(updatePurchaseInfoReqVO.getPurchaseStatus());
        purchaseInfoDO.setUpdateBy(SecurityUtils.getUserId());
        purchaseInfoDO.setUpdateByName(SecurityUtils.getUsername());
        purchaseInfoDO.setUpdateTime(LocalDateTime.now());
        // 计算商品数量和商品信息
        if (updatePurchaseInfoReqVO.getDetails() != null && !updatePurchaseInfoReqVO.getDetails().isEmpty()) {
            purchaseInfoDO.setGoodsQuantity(updatePurchaseInfoReqVO.getDetails().size());
            // 这里可以设置商品信息的汇总，比如商品名称列表等
            StringBuilder goodsInfo = new StringBuilder();
            for (int i = 0; i < updatePurchaseInfoReqVO.getDetails().size(); i++) {
                UpdatePurchaseInfoDetailsReqVO updatePurchaseInfoDetailsReqVO = updatePurchaseInfoReqVO.getDetails().get(i);
                goodsInfo.append(updatePurchaseInfoDetailsReqVO.getGoodsName());
                goodsInfo.append(" ");
                goodsInfo.append(updatePurchaseInfoDetailsReqVO.getSpecification());
                goodsInfo.append(" ");
                goodsInfo.append(updatePurchaseInfoDetailsReqVO.getModel());
                if (i != updatePurchaseInfoReqVO.getDetails().size() - 1) {
                    goodsInfo.append(",");
                }
            }
            purchaseInfoDO.setGoodsInfo(goodsInfo.toString());
        }
        // 保存采购单
        baseMapper.insert(purchaseInfoDO);

        // 保存采购单详情
        List<PurchaseInfoDetailsDO> purchaseInfoDetailsDOList = new ArrayList<>();
        for (UpdatePurchaseInfoDetailsReqVO detail : updatePurchaseInfoReqVO.getDetails()) {
            PurchaseInfoDetailsDO purchaseInfoDetailsDO = new PurchaseInfoDetailsDO();
            purchaseInfoDetailsDO.setPurchaseId(purchaseInfoDO.getId());
            purchaseInfoDetailsDO.setGoodsId(detail.getGoodsId());
            purchaseInfoDetailsDO.setGoodsAttributeId(detail.getGoodsAttributeId());
            purchaseInfoDetailsDO.setUnitId(detail.getUnitId());
            purchaseInfoDetailsDO.setGoodsBarcode(detail.getGoodsBarcode());
            purchaseInfoDetailsDO.setGoodsName(detail.getGoodsName());
            purchaseInfoDetailsDO.setSpecification(detail.getSpecification());
            purchaseInfoDetailsDO.setQuantity(detail.getQuantity());
            purchaseInfoDetailsDO.setPurchasedNumber(detail.getPurchasedNumber());
            purchaseInfoDetailsDO.setModel(detail.getModel());
            purchaseInfoDetailsDO.setColour(detail.getColour());
            purchaseInfoDetailsDO.setBrandName(detail.getBrandName());
            purchaseInfoDetailsDO.setManufacturer(detail.getManufacturer());
            purchaseInfoDetailsDO.setUnitName(detail.getUnitName());
            purchaseInfoDetailsDO.setGoodsSku(detail.getGoodsSku());
            purchaseInfoDetailsDO.setOthersInfo(detail.getOthersInfo());
            purchaseInfoDetailsDO.setPurchasePrice(detail.getPurchasePrice());
            purchaseInfoDetailsDO.setRemake(detail.getRemake());
            purchaseInfoDetailsDO.setTotalPrice(detail.getTotalPrice());
            purchaseInfoDetailsDO.setTaxRate(detail.getTaxRate());
            purchaseInfoDetailsDO.setTaxAmount(detail.getTaxAmount());
            purchaseInfoDetailsDO.setTaxPrice(detail.getTaxPrice());

            purchaseInfoDetailsDOList.add(purchaseInfoDetailsDO);
        }

        // 批量保存采购单详情
        purchaseInfoDetailsService.saveBatch(purchaseInfoDetailsDOList);
        // 如果关联了请购单，需要更新请购单状态
        try {
            rocketMQProducer.sendMessageAsync(PurchaseSalesRocketMQConstants.RECEIPTS_STATUS_TOPIC, PurchaseSalesRocketMQConstants.RECEIPTS_STATUS_TAG, updatePurchaseInfoReqVO.getPurchaseNumber(), new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    String msgId = sendResult.getMsgId();
                    log.debug("消息发送成功 ===============> "+msgId);
                }

                @Override
                public void onException(Throwable throwable) {
                    log.error("消息发送失败 ===============> ｛｝",throwable);
                }
            });
        }catch (Exception e) {
            log.error("更新请购单状态失败 ===============> ｛｝",e);
        }
        return CommonResult.success();
    }

    /**
     * 获取采购单详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<PurchaseInfoResVO> getPurchaseInfo(Long id) {
        if (id == null ){
            return CommonResult.error(SystemErrorCodeConstants.PURCHASE_INFO_ID_IS_NULL);
        }
        PurchaseInfoDO purchaseInfoDO = baseMapper.selectById(id);
        if (purchaseInfoDO == null) {
            return CommonResult.error(SystemErrorCodeConstants.PURCHASE_INFO_ID_IS_ERR);
        }
        PurchaseInfoResVO bean = NetonBeanUtils.toBean(purchaseInfoDO, PurchaseInfoResVO.class);
        // 查询采购单详情
        List<PurchaseInfoDetailsDO> purchaseInfoDetailsDOList = purchaseInfoDetailsService.listByPurchaseInfoId(id);
        bean.setDetails(NetonBeanUtils.toBean(purchaseInfoDetailsDOList, PurchaseInfoDetailsResVO.class));
        return CommonResult.success(bean);
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
        if (id == null ){
            return CommonResult.error(SystemErrorCodeConstants.PURCHASE_INFO_ID_IS_NULL);
        }
        PurchaseInfoDO purchaseInfoDO = baseMapper.selectById(id);
        if (purchaseInfoDO == null) {
            return CommonResult.error(SystemErrorCodeConstants.PURCHASE_INFO_ID_IS_ERR);
        }
        purchaseInfoDO.setUpdateTime(LocalDateTime.now());
        purchaseInfoDO.setUpdateBy(SecurityUtils.getUserId());
        purchaseInfoDO.setIsDeleted(1);
        purchaseInfoDO.setUpdateByName(SecurityUtils.getUsername());
        updateById(purchaseInfoDO);
        return CommonResult.success();
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
        if (updatePurchaseInfoStatusReqVO == null) {
            return CommonResult.error(SystemErrorCodeConstants.PURCHASE_INFO_NULL_ERROR);
        }
        if (updatePurchaseInfoStatusReqVO.getId() == null) {
            return CommonResult.error(SystemErrorCodeConstants.PURCHASE_INFO_ID_IS_NULL);
        }
        if (updatePurchaseInfoStatusReqVO.getPurchaseStatus() == null) {
            return CommonResult.error(SystemErrorCodeConstants.PURCHASE_INFO_STATUS_IS_ERR);
        }
        PurchaseInfoDO purchaseInfoDO = baseMapper.selectById(updatePurchaseInfoStatusReqVO.getId());
        if (purchaseInfoDO == null) {
            return CommonResult.error(SystemErrorCodeConstants.PURCHASE_INFO_ID_IS_ERR);
        }
        if (updatePurchaseInfoStatusReqVO.getPurchaseStatus() >2 && purchaseInfoDO.getPurchaseStatus() > 2){
            return CommonResult.error(SystemErrorCodeConstants.PURCHASE_INFO_STATUS_IS_ERR);
        }
        purchaseInfoDO.setPurchaseStatus(updatePurchaseInfoStatusReqVO.getPurchaseStatus());
        purchaseInfoDO.setUpdateTime(LocalDateTime.now());
        purchaseInfoDO.setUpdateByName(SecurityUtils.getUsername());
        purchaseInfoDO.setUpdateBy(SecurityUtils.getUserId());
        updateById(purchaseInfoDO);
        return CommonResult.success();
    }

    /**
     * 采购单分页
     *
     * @param queryPurchaseInfoPageReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<PurchaseInfoPageResVO>> queryPurchaseInfoPage(QueryPurchaseInfoPageReqVO queryPurchaseInfoPageReqVO) {
        IPage<PurchaseInfoPageResVO> page = new Page<>();
        page.setCurrent(queryPurchaseInfoPageReqVO.getPage());
        page.setSize(queryPurchaseInfoPageReqVO.getSize());
        IPage<PurchaseInfoPageResVO> iPage = super.baseMapper.queryPurchaseInfoPage(page, queryPurchaseInfoPageReqVO);
        if (iPage.getRecords() != null && !iPage.getRecords().isEmpty()) {
            iPage.getRecords().forEach(purchaseInfoPageResVO -> {
                if (StringUtils.isNotBlank(purchaseInfoPageResVO.getPurchaseNumber())) {
                    String purchaseNumber = purchaseInfoPageResVO.getPurchaseNumber();
                    purchaseInfoPageResVO.setPurchaseNumber(purchaseNumber+"[请]");
                }
            });
        }
        PageUtil<PurchaseInfoPageResVO> pageUtil = new PageUtil<>();
        pageUtil.setPageList(iPage.getRecords());
        pageUtil.setTotal(iPage.getTotal());
        return CommonResult.success(pageUtil);
    }
}
