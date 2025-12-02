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
}
