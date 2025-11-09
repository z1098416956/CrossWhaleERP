package com.neton.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neton.entity.GoodsInventoryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
}
