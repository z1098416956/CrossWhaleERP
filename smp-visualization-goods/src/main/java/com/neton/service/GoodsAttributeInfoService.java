package com.neton.service;

import com.neton.common.CommonResult;
import com.neton.req.CreateGoodsInfoReqVO;

public interface GoodsAttributeInfoService {

    /**
     * 创建商品多属性
     */
    CommonResult<Void> createGoodsAttributeInfo(CreateGoodsInfoReqVO createGoodsInfoReqVO, Long goodsId);
}
