package com.cross.whale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cross.whale.common.CommonResult;
import com.cross.whale.entity.GoodsExtendDO;
import com.cross.whale.req.CreateGoodsInfoReqVO;
import com.cross.whale.req.UpdateGoodsInfoReqVO;
import com.cross.whale.res.GoodsExtendDetailsResVO;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
     * 批量删除
     * @param goodsIds
     */
    void batchDeleteGoodsExtend(List<Long> goodsIds);

    /**
     * 获取商品扩展信息
     * @param goodsId
     * @return
     */
    List<GoodsExtendDetailsResVO> getGoodsExtendDetails(Long goodsId);

    /**
     * 获取商品扩展信息
     * @param goodsIds
     * @return
     */
    Map<Long,List<String>> getGoodsExtendInfo(Set<Long> goodsIds);
}
