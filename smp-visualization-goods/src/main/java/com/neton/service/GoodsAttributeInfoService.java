package com.neton.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neton.common.CommonResult;
import com.neton.entity.GoodsAttributeInfoDO;
import com.neton.req.CreateGoodsInfoReqVO;
import com.neton.req.UpdateGoodsInfoReqVO;

public interface GoodsAttributeInfoService extends IService<GoodsAttributeInfoDO> {

    /**
     * 创建商品多属性
     */
    CommonResult<Void> createGoodsAttributeInfo(CreateGoodsInfoReqVO createGoodsInfoReqVO, Long goodsId);

    /**
     * 更新商品多属性
     * @param updateGoodsInfoReqVO
     */
    void updateGoodsAttributeInfo(UpdateGoodsInfoReqVO updateGoodsInfoReqVO);

    /**
     * 删除商品多属性
     * @param goodsId
     */
    void deleteGoodsAttributeInfo(Long goodsId);
}
