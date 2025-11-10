package com.neton.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neton.entity.GoodsAttributeInfoDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsAttributeInfoDao extends BaseMapper<GoodsAttributeInfoDO> {

    /**
     * 根据商品ID删除多属性
     * @param goodsId
     */
    void deleteByGoodsId(@Param("goodsId") Long goodsId);

    /**
     * 根据商品ID查询商品多属性
     * @param goodsId
     * @return
     */
    default List<GoodsAttributeInfoDO> findByGoodsId(Long goodsId) {
        LambdaQueryWrapper<GoodsAttributeInfoDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GoodsAttributeInfoDO::getGoodsId, goodsId);
        return selectList(queryWrapper);
    }


    /**
     * 根据商品ID查询商品多属性
     * @param goodsId
     * @return
     */
    default List<GoodsAttributeInfoDO> findByGoodsIds(List<Long> goodsId) {
        LambdaQueryWrapper<GoodsAttributeInfoDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(GoodsAttributeInfoDO::getGoodsId, goodsId);
        return selectList(queryWrapper);
    }
}
