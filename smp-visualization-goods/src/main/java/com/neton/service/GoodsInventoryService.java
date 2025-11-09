package com.neton.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neton.common.CommonResult;
import com.neton.entity.GoodsInventoryDO;
import com.neton.req.CreateGoodsInfoReqVO;
import com.neton.req.UpdateGoodsInfoReqVO;
import com.neton.res.GoodsInventoryDetailsResVO;

import java.util.List;

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
     * 获取商品库存列表
     * @param goodsId
     * @return
     */
    List<GoodsInventoryDetailsResVO> getGoodsInventoryList(Long goodsId);
}
