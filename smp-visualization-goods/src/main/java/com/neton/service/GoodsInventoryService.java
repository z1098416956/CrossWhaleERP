package com.neton.service;

import com.neton.common.CommonResult;
import com.neton.req.CreateGoodsInfoReqVO;

public interface GoodsInventoryService {

    /**
     * 创建商品与仓库关系
     * @param createGoodsInfoReqVO
     * @param goodsId
     * @return
     */
    CommonResult<Void> createGoodsInventory(CreateGoodsInfoReqVO createGoodsInfoReqVO,Long goodsId);
}
