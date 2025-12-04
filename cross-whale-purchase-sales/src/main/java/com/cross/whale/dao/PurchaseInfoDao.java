package com.cross.whale.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cross.whale.entity.PurchaseInfoDO;
import com.cross.whale.req.QueryPurchaseInfoPageReqVO;
import com.cross.whale.res.PurchaseInfoPageResVO;
import org.apache.ibatis.annotations.Param;

public interface PurchaseInfoDao extends BaseMapper<PurchaseInfoDO> {

    default List<PurchaseInfoDO> selectByReceiptsNumber(String receiptsNumber) {
        return selectList(Wrappers.lambdaQuery(PurchaseInfoDO.class).eq(PurchaseInfoDO::getReceiptsNumber, receiptsNumber));
    }

    /**
     * 采购单分页
     * @param page
     * @param queryPurchaseInfoPageReqVO
     * @return
     */
    IPage<PurchaseInfoPageResVO>queryPurchaseInfoPage(IPage<PurchaseInfoPageResVO> page,@Param("params") QueryPurchaseInfoPageReqVO queryPurchaseInfoPageReqVO);
}
