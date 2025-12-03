package com.cross.whale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cross.whale.dao.PurchaseInfoDetailsDao;
import com.cross.whale.entity.PurchaseInfoDetailsDO;
import com.cross.whale.service.PurchaseInfoDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseInfoDetailsServiceImpl extends ServiceImpl<PurchaseInfoDetailsDao, PurchaseInfoDetailsDO> implements PurchaseInfoDetailsService {
    /**
     * 根据请购单ID查询采购单详情
     *
     * @param purchaseInfoIds 请购单ID列表
     * @return
     */
    @Override
    public List<PurchaseInfoDetailsDO> listByPurchaseInfoIds(List<Long> purchaseInfoIds) {
        return baseMapper.listByPurchaseInfoIds(purchaseInfoIds);
    }

    /**
     * 根据采购单详情ID查询采购单详情
     *
     * @param purchaseId 采购单ID
     * @return
     */
    @Override
    public List<PurchaseInfoDetailsDO> listByPurchaseInfoId(Long purchaseId) {
        return baseMapper.listByPurchaseInfoId(purchaseId);
    }

    /**
     * 根据请购单详情id查询采购单详情
     *
     * @param receiptsDetailsIds
     * @return
     */
    @Override
    public List<PurchaseInfoDetailsDO> listByPurchaseInfoByReceiptsDetailsIds(List<Long> receiptsDetailsIds) {
        return baseMapper.listByPurchaseInfoByReceiptsDetailsIds(receiptsDetailsIds);
    }

    /**
     * 根据采购单ID删除采购单详情
     *
     * @param purchaseInfoId 采购单ID
     */
    @Override
    public void deleteByPurchaseInfoId(Long purchaseInfoId) {
        baseMapper.deleteByPurchaseInfoId(purchaseInfoId);
    }
}
