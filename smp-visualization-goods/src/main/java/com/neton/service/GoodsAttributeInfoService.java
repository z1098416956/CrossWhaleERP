package com.neton.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neton.common.CommonResult;
import com.neton.entity.GoodsAttributeInfoDO;
import com.neton.req.CreateGoodsInfoReqVO;
import com.neton.req.UpdateGoodsInfoReqVO;
import com.neton.res.GoodsAttributeInfoDetailsResVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    /**
     * 批量删除
     * @param goodsIds
     */
    void batchDeleteGoodsAttributeInfos(List<Long> goodsIds);


    /**
     * 获取商品多属性
     * @param goodsId
     * @return
     */
    List<GoodsAttributeInfoDetailsResVO> getGoodsAttributeInfoDetails(Long goodsId);

    /**
     * 获取商品多属性
     * @param goodsId
     * @return
     */
    Map<Long,List<GoodsAttributeInfoDetailsResVO>> getGoodsAttributeInfoByGoodsIds(Set<Long> goodsId);
}
