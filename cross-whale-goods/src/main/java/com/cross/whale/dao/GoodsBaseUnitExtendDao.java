package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cross.whale.entity.GoodsBaseUnitExtendDO;
import com.cross.whale.res.UnitExtendResVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsBaseUnitExtendDao extends BaseMapper<GoodsBaseUnitExtendDO> {

    void deleteByGoodsBaseUnitId(@Param("unitId") Long goodsBaseUnitId);

    void updateByGoodsBaseUnitId(@Param("unitId") Long goodsBaseUnitId,@Param("userId") Long userId);

    List<UnitExtendResVO> selectByGoodsBaseUnitIds(@Param("unitIds") List<Long> unitIds);
}
