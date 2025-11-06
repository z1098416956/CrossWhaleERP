package com.neton.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neton.entity.GoodsBaseUnitExtendDO;
import org.apache.ibatis.annotations.Param;

public interface GoodsBaseUnitExtendDao extends BaseMapper<GoodsBaseUnitExtendDO> {

    void deleteByGoodsBaseUnitId(@Param("unitId") Long goodsBaseUnitId);

    void updateByGoodsBaseUnitId(@Param("unitId") Long goodsBaseUnitId,@Param("userId") Long userId);
}
