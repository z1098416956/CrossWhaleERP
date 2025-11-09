package com.neton.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neton.common.CommonResult;
import com.neton.entity.GoodsAttributeDO;
import com.neton.req.CreateGoodsInfoReqVO;
import com.neton.req.UpdateGoodsInfoReqVO;

public interface GoodsAttributeService extends IService<GoodsAttributeDO> {

    /**
     * 创建商品选择的属性
     * @param createGoodsInfoReqVO
     * @param goodsId
     * @return
     */
    CommonResult<Void> createGoodsAttributeInfo(CreateGoodsInfoReqVO createGoodsInfoReqVO,Long goodsId);

    /**
     * 更新商品选择的属性
     * @param updateGoodsInfoReqVO
     */
    void updateGoodsAttributeInfo(UpdateGoodsInfoReqVO updateGoodsInfoReqVO);

    /**
     * 删除选择的属性
     * @param goodsId
     */
    void deleteGoodsAttributeInfo(Long goodsId);
}
