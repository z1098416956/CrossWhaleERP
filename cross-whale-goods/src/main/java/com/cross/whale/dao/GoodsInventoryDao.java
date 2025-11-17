package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cross.whale.entity.GoodsInventoryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface GoodsInventoryDao extends BaseMapper<GoodsInventoryDO> {

    /**
     * 批量更新商品库存信息
     * @param list
     */
    void updateBatch(@Param("list") List<GoodsInventoryDO> list);

    /**
     * 根据商品id查询商品库存
     * @param goodsId
     * @return
     */
    default List<GoodsInventoryDO> queryGoodsInventory(Long goodsId) {
        LambdaQueryWrapper<GoodsInventoryDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsInventoryDO::getGoodsId, goodsId);
        return selectList(wrapper);
    }

    /**
     * 根据商品ids查询商品库存
     * @param goodsIds
     * @return
     */
    default List<GoodsInventoryDO> queryGoodsInventoryByGoodsIds(Set<Long> goodsIds) {
        LambdaQueryWrapper<GoodsInventoryDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(GoodsInventoryDO::getGoodsId, goodsIds);
        return selectList(wrapper);
    }


    /**
     * 根据商品ids查询商品库存
     * @param goodsIds
     * @return
     */
    default List<GoodsInventoryDO> queryGoodsInventoryByGoodsIds(List<Long> goodsIds) {
        LambdaQueryWrapper<GoodsInventoryDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(GoodsInventoryDO::getGoodsId, goodsIds);
        return selectList(wrapper);
    }
}
