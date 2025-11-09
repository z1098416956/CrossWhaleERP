package com.neton.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neton.entity.GoodsInfoDO;

import java.util.List;

public interface GoodsInfoDao extends BaseMapper<GoodsInfoDO> {

    /**
     * 检查商品名称是否重复
     * @param goodsName
     * @return
     */
    default List<GoodsInfoDO> checkGoodsNameRepeat(String goodsName) {
        LambdaQueryWrapper<GoodsInfoDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GoodsInfoDO::getGoodsName,goodsName);
        return selectList(queryWrapper);
    }
}
