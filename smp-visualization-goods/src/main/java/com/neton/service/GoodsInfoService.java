package com.neton.service;

import com.neton.common.CommonResult;
import com.neton.req.CreateGoodsInfoReqVO;

public interface GoodsInfoService {

    /**
     * 创建商品信息
     * @param createGoodsInfoReqVO
     * @return
     */
    CommonResult<Void> createGoodsInfo(CreateGoodsInfoReqVO createGoodsInfoReqVO);
}
