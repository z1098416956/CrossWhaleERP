package com.cross.whale.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cross.whale.entity.PurchaseInfoDetailsDO;

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
}
