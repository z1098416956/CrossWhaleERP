package com.cross.whale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cross.whale.common.CommonResult;
import com.cross.whale.entity.GoodsInventoryDO;
import com.cross.whale.req.CreateGoodsInfoReqVO;
import com.cross.whale.req.UpdateGoodsInfoReqVO;
import com.cross.whale.res.GoodsInventoryDetailsResVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GoodsInventoryService extends IService<GoodsInventoryDO> {

    /**
     * 创建商品与仓库关系
     * @param createGoodsInfoReqVO
     * @param goodsId
     * @return
     */
    CommonResult<Void> createGoodsInventory(CreateGoodsInfoReqVO createGoodsInfoReqVO,Long goodsId);

    /**
     * 更新商品与仓库
     * @param updateGoodsInfoReqVO
     */
    void updateGoodsInventory(UpdateGoodsInfoReqVO updateGoodsInfoReqVO);


    /**
     * 删除商品与库存
     * @param goodsId
     */
    void deleteGoodsInventory(Long goodsId);

    /**
     * 批量删除
     * @param goodsIds
     */
    void batchDeleteGoodsInventory(List<Long> goodsIds);

    /**
     * 获取商品库存列表
     * @param goodsId
     * @return
     */
    List<GoodsInventoryDetailsResVO> getGoodsInventoryList(Long goodsId);

    /**
     * 获取商品库存
     * @param goodsIds
     * @return
     */
    Map<Long,Long> getGoodsInventoryCount(Set<Long> goodsIds);
}
