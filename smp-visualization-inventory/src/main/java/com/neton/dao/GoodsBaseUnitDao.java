package com.neton.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neton.entity.GoodsBaseUnitDO;
import com.neton.req.QueryUnitReqVO;
import com.neton.res.UnitResVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsBaseUnitDao extends BaseMapper<GoodsBaseUnitDO> {

    IPage<UnitResVO>  getGoodsBaseUnitPage(IPage<UnitResVO> page, @Param("queryUnitReqVO") QueryUnitReqVO queryUnitReqVO);


    UnitResVO getUnitById(@Param("id") Long id);

    /**
     * 批量根据id更新
     * @param list
     */
    void updateBatchBaGoodsBaseUnit(List<GoodsBaseUnitDO> list);
}
