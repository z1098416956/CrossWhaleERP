package com.cross.whale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cross.whale.common.CommonResult;
import com.cross.whale.entity.GoodsAttributeDO;
import com.cross.whale.req.CreateGoodsInfoReqVO;
import com.cross.whale.req.UpdateGoodsInfoReqVO;
import com.cross.whale.res.GoodsAttributeDetailsResVO;

import java.util.List;

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

    /**
     * 批量删除
     * @param goodsIds
     */
    void batchDeleteGoodsAttributeInfos(List<Long> goodsIds);

    /**
     * 获取商品选中的属性
     * @param goodsId
     * @return
     */
    List<GoodsAttributeDetailsResVO> getGoodsAttributeDetailsByGoodsId(Long goodsId);
}
