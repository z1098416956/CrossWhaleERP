package com.cross.whale.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cross.whale.entity.PurchaseInfoDO;

public interface PurchaseInfoDao extends BaseMapper<PurchaseInfoDO> {

    default List<PurchaseInfoDO> selectByReceiptsNumber(String receiptsNumber) {
        return selectList(Wrappers.lambdaQuery(PurchaseInfoDO.class).eq(PurchaseInfoDO::getReceiptsNumber, receiptsNumber));
    }

}
