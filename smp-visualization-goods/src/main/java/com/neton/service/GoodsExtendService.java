package com.neton.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neton.common.CommonResult;
import com.neton.entity.GoodsExtendDO;
import com.neton.req.CreateGoodsInfoReqVO;
import com.neton.req.UpdateGoodsInfoReqVO;
import com.neton.res.GoodsExtendDetailsResVO;

import java.util.List;

public interface GoodsExtendService extends IService<GoodsExtendDO> {

    /**
     * 创建商品扩展信息
     *
     * @param createGoodsInfoReqVO
     * @return
     */
    CommonResult<Void> createGoodsExtend(CreateGoodsInfoReqVO createGoodsInfoReqVO,Long goodsId);

    /**
     * 更新商品扩展信息
     * @param updateGoodsInfoReqVO
     */
    void updateGoodsExtend(UpdateGoodsInfoReqVO updateGoodsInfoReqVO);

    /**
     * 删除商品扩展信息
     * @param goodsId
     */
    void deleteGoodsExtend(Long goodsId);

    /**
     * 获取商品扩展信息
     * @param goodsId
     * @return
     */
    List<GoodsExtendDetailsResVO> getGoodsExtendDetails(Long goodsId);
}
