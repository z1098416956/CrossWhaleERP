package com.cross.whale.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cross.whale.entity.PurchaseInfoDetailsDO;


public interface PurchaseInfoDetailsService extends IService<PurchaseInfoDetailsDO> {

    /**
     * 根据请购单ID查询采购单详情
     * @param purchaseInfoIds 请购单ID列表
     * @return
     */
    List<PurchaseInfoDetailsDO> listByPurchaseInfoIds(List<Long> purchaseInfoIds);

    /**
     * 根据采购单详情ID查询采购单详情
     * @param purchaseId 采购单ID
     * @return
     */
    List<PurchaseInfoDetailsDO> listByPurchaseInfoId(Long purchaseId);

    /**
     * 根据请购单详情id查询采购单详情
     * @param receiptsDetailsIds
     * @return
     */
    List<PurchaseInfoDetailsDO> listByPurchaseInfoByReceiptsDetailsIds(List<Long> receiptsDetailsIds);

    /**
     * 根据采购单ID删除采购单详情
     * @param purchaseInfoId 采购单ID
     */
    void deleteByPurchaseInfoId(Long purchaseInfoId);
}
