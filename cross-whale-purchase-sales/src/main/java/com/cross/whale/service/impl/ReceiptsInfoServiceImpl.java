package com.cross.whale.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.common.ServiceException;
import com.cross.whale.common.SystemErrorCodeConstants;
import com.cross.whale.dao.ReceiptsInfoDao;
import com.cross.whale.entity.PurchaseInfoDetailsDO;
import com.cross.whale.entity.ReceiptsInfoDO;
import com.cross.whale.entity.ReceiptsInfoDetailsDO;
import com.cross.whale.feign.StorageClient;
import com.cross.whale.req.*;
import com.cross.whale.res.ReceiptsInfoDetailsResVO;
import com.cross.whale.res.ReceiptsInfoPageResVO;
import com.cross.whale.res.ReceiptsInfoResVO;
import com.cross.whale.res.StorageVO;
import com.cross.whale.service.PurchaseInfoDetailsService;
import com.cross.whale.service.ReceiptsInfoDetailsService;
import com.cross.whale.service.ReceiptsInfoService;
import com.cross.whale.utils.PurchaseSalesStatusConstants;
import com.cross.whale.utils.SecurityUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReceiptsInfoServiceImpl extends ServiceImpl<ReceiptsInfoDao, ReceiptsInfoDO> implements ReceiptsInfoService {

    @Autowired
    private ReceiptsInfoDetailsService receiptsInfoDetailsService;

    @Autowired
    private PurchaseInfoDetailsService purchaseInfoDetailsService;

    @Autowired
    private StorageClient storageClient;

    /**
     * 添加请购单
     *
     * @param createReceiptsInfoReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> createReceiptsInfo(CreateReceiptsInfoReqVO createReceiptsInfoReqVO) {
        ReceiptsInfoDO receiptsInfoDO = new ReceiptsInfoDO();
        if (createReceiptsInfoReqVO.getDetails() == null || createReceiptsInfoReqVO.getDetails().isEmpty()) {
            return CommonResult.error(SystemErrorCodeConstants.RECEIPTS_GOODS_DETAILS_INFO_IS_NULL);
        }
        if (createReceiptsInfoReqVO.getReceiptsStatus() == null) {
            return CommonResult.error(SystemErrorCodeConstants.RECEIPTS_STATUS_IS_NULL);
        }
        if (createReceiptsInfoReqVO.getReceiptsStatus() >= 2 ){
            return CommonResult.error(SystemErrorCodeConstants.RECEIPTS_STATUS_IS_ERR);
        }
        if (createReceiptsInfoReqVO.getWarehouseId() != null) {
            CommonResult<StorageVO> info = storageClient.getStorageDetailInfo(createReceiptsInfoReqVO.getWarehouseId());
            if (info.getCode() != 0){
                return CommonResult.error(info.getCode(),info.getMessage());
            }
            if (info.getData() == null){
                return CommonResult.error(SystemErrorCodeConstants.STORAGE_IS_NONENTITY);
            }
        }
        BeanUtils.copyProperties(createReceiptsInfoReqVO, receiptsInfoDO);
        List<CreateReceiptsInfoDetailsReqVO> details = createReceiptsInfoReqVO.getDetails();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < details.size(); i++) {
            CreateReceiptsInfoDetailsReqVO detail = details.get(i);
            sb.append(detail.getGoodsName()+" ");
            sb.append(detail.getSpecification());
            sb.append(" ");
            sb.append(detail.getModel()+ " ");
            sb.append(detail.getColour());
            if (i < details.size() - 1) {
                sb.append(" | ");
            }
        }
        receiptsInfoDO.setGoodsQuantity(details.size());
        receiptsInfoDO.setReceiptsTime(LocalDateTime.now());
        receiptsInfoDO.setApplicantId(SecurityUtils.getUserId());
        receiptsInfoDO.setApplicantName(SecurityUtils.getUsername());
        receiptsInfoDO.setGoodsInfo(sb.toString());
        receiptsInfoDO.setReceiptsStatus(createReceiptsInfoReqVO.getReceiptsStatus() == null ? 0 : createReceiptsInfoReqVO.getReceiptsStatus());
        receiptsInfoDO.setCreateBy(SecurityUtils.getUserId());
        receiptsInfoDO.setUpdateBy(SecurityUtils.getUserId());
        receiptsInfoDO.setIsDeleted(0);
        receiptsInfoDO.setCreateByName(SecurityUtils.getUsername());
        receiptsInfoDO.setUpdateByName(SecurityUtils.getUsername());
        receiptsInfoDO.setCreateTime(LocalDateTime.now());
        receiptsInfoDO.setUpdateTime(LocalDateTime.now());
        baseMapper.insert(receiptsInfoDO);
        List<ReceiptsInfoDetailsDO> res = createReceiptsInfoReqVO.getDetails().stream().map(req -> {
            ReceiptsInfoDetailsDO receiptsInfoDetailsDO = new ReceiptsInfoDetailsDO();
            BeanUtils.copyProperties(req, receiptsInfoDetailsDO);
            receiptsInfoDetailsDO.setReceiptsId(receiptsInfoDO.getId());
            receiptsInfoDetailsDO.setIsDeleted(0);
            receiptsInfoDetailsDO.setCreateBy(SecurityUtils.getUserId());
            receiptsInfoDetailsDO.setUpdateBy(SecurityUtils.getUserId());
            receiptsInfoDetailsDO.setCreateTime(LocalDateTime.now());
            receiptsInfoDetailsDO.setUpdateTime(LocalDateTime.now());
            receiptsInfoDetailsDO.setCreateByName(SecurityUtils.getUsername());
            receiptsInfoDetailsDO.setUpdateByName(SecurityUtils.getUsername());
            return receiptsInfoDetailsDO;
        }).collect(Collectors.toList());
        receiptsInfoDetailsService.saveBatch(res);
        return CommonResult.success();
    }

    /**
     * 获取请购单详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<ReceiptsInfoResVO> getReceiptsDetailsInfo(Long id) {
        ReceiptsInfoDO infoDO = getById(id);
        if (infoDO == null) {
            return CommonResult.error(SystemErrorCodeConstants.RECEIPTS_ID_IS_ERR);
        }
        ReceiptsInfoResVO receiptsInfoResVO = new ReceiptsInfoResVO();
        BeanUtils.copyProperties(infoDO, receiptsInfoResVO);

        List<ReceiptsInfoDetailsResVO> list = receiptsInfoDetailsService.listReceiptsInfoDetails(id);
        receiptsInfoResVO.setReceiptsInfoDetails(list);
        return CommonResult.success(receiptsInfoResVO);
    }

    /**
     * 删除请购单
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> deleteReceiptsInfo(Long id) {
        ReceiptsInfoDO infoDO = getById(id);
        if (infoDO == null) {
            return CommonResult.error(SystemErrorCodeConstants.RECEIPTS_ID_IS_ERR);
        }
        infoDO.setIsDeleted(1);
        infoDO.setUpdateBy(SecurityUtils.getUserId());
        infoDO.setUpdateTime(LocalDateTime.now());
        infoDO.setUpdateByName(SecurityUtils.getUsername());
        baseMapper.updateById(infoDO);
        receiptsInfoDetailsService.deleteReceiptsInfoDetails(id);
        return CommonResult.success();
    }

    /**
     * 更新请购单状态
     *
     * @param updateReceiptsStatusReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> updateReceiptsStatus(UpdateReceiptsStatusReqVO updateReceiptsStatusReqVO) {
        List<ReceiptsInfoDO> list = baseMapper.selectByIds(updateReceiptsStatusReqVO.getIds());
        if (list == null || list.isEmpty()) {
            return CommonResult.error(SystemErrorCodeConstants.RECEIPTS_ID_IS_ERR);
        }
        list.forEach(item -> {
            item.setUpdateByName(SecurityUtils.getUsername());
            item.setUpdateTime(LocalDateTime.now());
            item.setUpdateBy(SecurityUtils.getUserId());
            item.setReceiptsStatus(updateReceiptsStatusReqVO.getReceiptsStatus());
        });
        updateBatchById(list);
        return CommonResult.success();
    }

    /**
     * 删除单据
     *
     * @param deleteReceiptsReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> deleteReceipts(DeleteReceiptsReqVO deleteReceiptsReqVO) {
        List<ReceiptsInfoDO> list = baseMapper.selectByIds(deleteReceiptsReqVO.getIds());
        if (list == null || list.isEmpty()) {
            return CommonResult.error(SystemErrorCodeConstants.RECEIPTS_ID_IS_ERR);
        }
        list.forEach(item -> {
            item.setUpdateByName(SecurityUtils.getUsername());
            item.setUpdateTime(LocalDateTime.now());
            item.setUpdateBy(SecurityUtils.getUserId());
            item.setIsDeleted(1);
        });
        updateBatchById(list);
        return CommonResult.success();
    }

    /**
     * 请购单列表
     *
     * @param queryReceiptsReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<ReceiptsInfoPageResVO>> queryReceiptsPage(QueryReceiptsReqVO queryReceiptsReqVO) {
        IPage<ReceiptsInfoPageResVO> page = new Page<>();
        page.setCurrent(queryReceiptsReqVO.getPage());
        page.setSize(queryReceiptsReqVO.getSize());
        IPage<ReceiptsInfoPageResVO> resultPage = baseMapper.queryReceiptsPage(page, queryReceiptsReqVO);
        PageUtil<ReceiptsInfoPageResVO> pageUtil = new PageUtil<>();
        pageUtil.setPageList(resultPage.getRecords());
        pageUtil.setTotal(resultPage.getTotal());
        return CommonResult.success(pageUtil);
    }

    /**
     * 更新单据信息
     *
     * @param updateReceiptsInfoReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> updateReceiptsInfo(UpdateReceiptsInfoReqVO updateReceiptsInfoReqVO) {
        if (updateReceiptsInfoReqVO.getId() == null) {
            return CommonResult.error(SystemErrorCodeConstants.RECEIPTS_ID_IS_NULL);
        }
        ReceiptsInfoDO infoDO = getById(updateReceiptsInfoReqVO.getId());
        if (infoDO == null) {
            return CommonResult.error(SystemErrorCodeConstants.RECEIPTS_ID_IS_ERR);
        }
        BeanUtils.copyProperties(updateReceiptsInfoReqVO, infoDO);
        infoDO.setUpdateBy(SecurityUtils.getUserId());
        infoDO.setUpdateTime(LocalDateTime.now());
        infoDO.setUpdateByName(SecurityUtils.getUsername());
        baseMapper.updateById(infoDO);
        receiptsInfoDetailsService.updateReceiptsInfoDetails(updateReceiptsInfoReqVO);
        return CommonResult.success();
    }

    @Override
    public ReceiptsInfoResVO getPurchaseInfoByPurchaseNumber(String purchaseNumber) {
        if (StringUtils.isBlank(purchaseNumber)) {
            throw new ServiceException(SystemErrorCodeConstants.RECEIPTS_PURCHASE_NUMBER_IS_NULL);
        }
        ReceiptsInfoDO infoDO = baseMapper.getPurchaseInfoByPurchaseNumber(purchaseNumber);
        if (infoDO == null) {
            throw new ServiceException(SystemErrorCodeConstants.RECEIPTS_PURCHASE_NUMBER_IS_ERROR);
        }
        ReceiptsInfoResVO resVO = new ReceiptsInfoResVO();
        BeanUtils.copyProperties(infoDO, resVO);
        // 采购单详情
        List<ReceiptsInfoDetailsResVO> list = receiptsInfoDetailsService.listReceiptsInfoDetails(infoDO.getId());
        resVO.setReceiptsInfoDetails(list);
        return resVO;
    }

    /**
     * 根据id更新请购单状态
     *
     * @param id
     * @param status
     */
    @Override
    public void updateReceiptsStatusById(Long id, Integer status) {
        if (id == null) {
            throw new ServiceException(SystemErrorCodeConstants.RECEIPTS_ID_IS_NULL);
        }
        if (status == null) {
            throw new ServiceException(SystemErrorCodeConstants.RECEIPTS_STATUS_IS_NULL);
        }
        ReceiptsInfoDO infoDO = getById(id);
        if (infoDO == null) {
            throw new ServiceException(SystemErrorCodeConstants.RECEIPTS_ID_IS_ERR);
        }
        infoDO.setReceiptsStatus(status);
        infoDO.setUpdateBy(SecurityUtils.getUserId());
        infoDO.setUpdateTime(LocalDateTime.now());
        infoDO.setUpdateByName(SecurityUtils.getUsername());
        baseMapper.updateById(infoDO);
    }

    /**
     * 根据请购单ID
     *
     * @param purchaseNumber
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateReceiptsStatusByPurchaseNumber(String purchaseNumber) {
        if (StringUtils.isBlank(purchaseNumber)) {
            return;
        }
        ReceiptsInfoDO receiptsInfoDO = baseMapper.getPurchaseInfoByPurchaseNumber(purchaseNumber);
        if (receiptsInfoDO == null) {
            return;
        }
        //请购单详情
        List<ReceiptsInfoDetailsResVO> list = receiptsInfoDetailsService.listReceiptsInfoDetails(receiptsInfoDO.getId());
        if (list == null || list.isEmpty()) {
            return;
        }
        List<Long> receiptsDetailsIds = list.stream().map(ReceiptsInfoDetailsResVO::getId).toList();
        //根据请购单详情id查询是否采购了
        List<PurchaseInfoDetailsDO> detailsDOList = purchaseInfoDetailsService.listByPurchaseInfoByReceiptsDetailsIds(receiptsDetailsIds);
        if (detailsDOList == null || detailsDOList.isEmpty()) {
            return;
        }
        //根据详情id分组
        Map<Long, List<PurchaseInfoDetailsDO>> map = detailsDOList.stream().collect(Collectors.groupingBy(PurchaseInfoDetailsDO::getReceiptsDetailsId));
        Map<Long,Integer> goodsSum = new HashMap<>();
        for (Long id : map.keySet()) {
            List<PurchaseInfoDetailsDO> doList = map.get(id);
            if (doList == null || doList.isEmpty()) {
                continue;
            }
            Integer sum = 0;
            for (PurchaseInfoDetailsDO purchaseInfoDetailsDO : doList) {
                sum += purchaseInfoDetailsDO.getPurchasedNumber() == null ? 0 : purchaseInfoDetailsDO.getPurchasedNumber().intValue();
            }
            goodsSum.put(id, sum);
        }
        if (goodsSum.size() != receiptsDetailsIds.size()) {
            receiptsInfoDO.setReceiptsStatus(PurchaseSalesStatusConstants.RequisitionStatus.PARTIALLY_PURCHASE_COMPLETED.getCode());
        }else {
            boolean isStatus = true;
            for (PurchaseInfoDetailsDO purchaseInfoDetailsDO : detailsDOList){
                Integer sum = goodsSum.get(purchaseInfoDetailsDO.getId());
                if (sum == null) {
                    isStatus = false;
                    break;
                }else {
                    int value = purchaseInfoDetailsDO.getQuantity().intValue();
                    if (sum.intValue() != value) {
                        isStatus = false;
                    }
                }
            }
            if (isStatus) {
                receiptsInfoDO.setReceiptsStatus(PurchaseSalesStatusConstants.RequisitionStatus.PURCHASE_COMPLETED.getCode());
            }else {
                receiptsInfoDO.setReceiptsStatus(PurchaseSalesStatusConstants.RequisitionStatus.PARTIALLY_PURCHASE_COMPLETED.getCode());
            }
        }
        receiptsInfoDO.setUpdateBy(1l);
        receiptsInfoDO.setUpdateTime(LocalDateTime.now());
        receiptsInfoDO.setUpdateByName("system");
        baseMapper.updateById(receiptsInfoDO);
    }
}
