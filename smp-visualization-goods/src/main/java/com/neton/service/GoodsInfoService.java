package com.neton.service;

import com.neton.common.CommonResult;
import com.neton.req.CreateGoodsInfoReqVO;
import com.neton.req.UpdateGoodsInfoReqVO;

public interface GoodsInfoService {

    /**
     * 创建商品信息
     * @param createGoodsInfoReqVO
     * @return
     */
    CommonResult<Void> createGoodsInfo(CreateGoodsInfoReqVO createGoodsInfoReqVO);

    /**
     * 更新商品信息
     * @param updateGoodsInfoReqVO
     * @return
     */
    CommonResult<Void> updateGoodsInfo(UpdateGoodsInfoReqVO updateGoodsInfoReqVO);

    /**
     * 删除商品信息
     * @param goodsId
     * @return
     */
    CommonResult<Void> deleteGoodsInfo(Long goodsId);
}
