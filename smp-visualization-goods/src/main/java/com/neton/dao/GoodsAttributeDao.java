package com.neton.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neton.entity.GoodsAttributeDO;
import com.neton.mybatis.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsAttributeDao extends BaseMapper<GoodsAttributeDO> {

    /**
     * 删除商品选择的属性
     * @param goodsId
     */
    void deleteByGoodsId(@Param("goodsId") Long goodsId);

    /**
     * 根据商品ID查询选择的属性
     * @param goodsId
     * @return
     */
    default List<GoodsAttributeDO> selectByGoodsId(Long goodsId) {
        LambdaQueryWrapperX<GoodsAttributeDO> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(GoodsAttributeDO::getGoodsId, goodsId);
        return selectList(queryWrapperX);
    }
}
