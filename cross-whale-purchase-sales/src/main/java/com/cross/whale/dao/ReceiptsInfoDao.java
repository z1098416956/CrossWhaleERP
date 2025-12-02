package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cross.whale.entity.ReceiptsInfoDO;
import com.cross.whale.req.QueryReceiptsReqVO;
import com.cross.whale.res.ReceiptsInfoPageResVO;
import org.apache.ibatis.annotations.Param;

public interface ReceiptsInfoDao extends BaseMapper<ReceiptsInfoDO> {

    IPage<ReceiptsInfoPageResVO> queryReceiptsPage(IPage<ReceiptsInfoPageResVO>page,@Param("params") QueryReceiptsReqVO queryReceiptsReqVO);


    /**
     * 根据采购单编号查询采购单详情
     * @param purchaseNumber 采购单编号
     * @return 采购单详情
     */
    default ReceiptsInfoDO getPurchaseInfoByPurchaseNumber(String purchaseNumber) {
        return selectOne(new LambdaQueryWrapper<ReceiptsInfoDO>()
                .eq(ReceiptsInfoDO::getReceiptsNumber, purchaseNumber));
    }
}
