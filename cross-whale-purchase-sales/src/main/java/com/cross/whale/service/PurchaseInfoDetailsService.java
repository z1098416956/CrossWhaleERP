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


}
