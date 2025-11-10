package com.neton.service.goods;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.*;
import com.neton.res.GoodsInfoDetailsResVO;
import com.neton.res.GoodsInfoPageResVO;

public interface WebGoodsInfoService {

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

    /**
     * 获取商品信息详情
     * @param goodsId
     * @return
     */
    CommonResult<GoodsInfoDetailsResVO> getGoodsInfoDetailsById(Long goodsId);


    /**
     * 更新商品状态
     * @param updateGoodsInfoStatusReqVO
     * @return
     */
    CommonResult<Void> updateGoodsInfoStatus(UpdateGoodsInfoStatusReqVO updateGoodsInfoStatusReqVO);


    /**
     * 商品分页查询
     * @param queryGoodsInfoReqVO
     * @return
     */
    CommonResult<PageUtil<GoodsInfoPageResVO>> queryGoodsInfoPage(QueryGoodsInfoReqVO queryGoodsInfoReqVO);


    /**
     * 批量删除
     * @param deleteBatchGoodsReqVO
     * @return
     */
    CommonResult<Void> batchDeleteGoodsInfos(DeleteBatchGoodsReqVO deleteBatchGoodsReqVO);
}
