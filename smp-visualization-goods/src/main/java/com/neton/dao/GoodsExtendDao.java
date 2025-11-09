package com.neton.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neton.entity.GoodsExtendDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
}
