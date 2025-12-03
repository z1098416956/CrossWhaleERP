package com.cross.whale.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cross.whale.entity.PurchaseInfoDetailsDO;
import org.apache.ibatis.annotations.Param;

public interface PurchaseInfoDetailsDao extends BaseMapper<PurchaseInfoDetailsDO> {

    /**
     * 根据请购单ID查询采购单详情
     * @param purchaseInfoIds 请购单ID列表
     * @return
     */
    default List<PurchaseInfoDetailsDO> listByPurchaseInfoIds(List<Long> purchaseInfoIds){
        return selectList(new LambdaQueryWrapper<PurchaseInfoDetailsDO>()
                .in(PurchaseInfoDetailsDO::getPurchaseId, purchaseInfoIds));
    }

    /**
     * 根据请购单ID查询采购单详情
     * @param purchaseId 请购单ID
     * @return
     */
    default List<PurchaseInfoDetailsDO> listByPurchaseInfoId(Long purchaseId){

        return selectList(new LambdaQueryWrapper<PurchaseInfoDetailsDO>()
                .eq(PurchaseInfoDetailsDO::getPurchaseId, purchaseId));
    }

    /**
     * 根据请购单详情id查询采购单详情
     * @param receiptsDetailsIds
     * @return
     */
    default  List<PurchaseInfoDetailsDO> listByPurchaseInfoByReceiptsDetailsIds(List<Long> receiptsDetailsIds){
        return selectList(new LambdaQueryWrapper<PurchaseInfoDetailsDO>()
                .in(PurchaseInfoDetailsDO::getReceiptsDetailsId, receiptsDetailsIds));
    }

    /**
     * 根据采购单ID删除采购单详情
     * @param purchaseInfoId 采购单ID
     */
    void deleteByPurchaseInfoId(@Param("purchaseInfoId") Long purchaseInfoId);
}
