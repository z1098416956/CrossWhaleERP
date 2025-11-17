package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cross.whale.entity.GoodsExtendDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface GoodsExtendDao extends BaseMapper<GoodsExtendDO> {

    /**
     * 删除商品扩展
     * @param goodsId
     */
    void deleteByGoodsId(@Param("goodsId") Long goodsId);


    /**
     * 根据商品ID查询选择的扩展信息
     * @param goodsId
     * @return
     */
    default List<GoodsExtendDO> selectByGoodsId(Long goodsId) {
        LambdaQueryWrapper<GoodsExtendDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GoodsExtendDO::getGoodsId, goodsId);
        return selectList(queryWrapper);
    }

    /**
     * 根据商品ID查询选择的扩展信息
     * @param goodsId
     * @return
     */
    default List<GoodsExtendDO> selectByGoodsIds(List<Long> goodsId) {
        LambdaQueryWrapper<GoodsExtendDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(GoodsExtendDO::getGoodsId, goodsId);
        return selectList(queryWrapper);
    }


    /**
     * 根据商品ID查询选择的扩展信息
     * @param goodsId
     * @return
     */
    default List<GoodsExtendDO> selectByGoodsIds(Set<Long> goodsId) {
        LambdaQueryWrapper<GoodsExtendDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(GoodsExtendDO::getGoodsId, goodsId);
        return selectList(queryWrapper);
    }
}
