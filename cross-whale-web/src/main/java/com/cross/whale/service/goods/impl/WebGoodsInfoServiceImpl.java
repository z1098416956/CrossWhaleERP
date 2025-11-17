package com.cross.whale.service.goods.impl;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.feign.goods.GoodsClient;
import com.cross.whale.req.*;
import com.cross.whale.res.GoodsInfoDetailsResVO;
import com.cross.whale.res.GoodsInfoPageResVO;
import com.cross.whale.service.goods.WebGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebGoodsInfoServiceImpl implements WebGoodsInfoService {

    @Autowired
    private GoodsClient goodsClient;

    /**
     * 创建商品信息
     *
     * @param createGoodsInfoReqVO
     * @return
     */
    @Override
    public CommonResult<Void> createGoodsInfo(CreateGoodsInfoReqVO createGoodsInfoReqVO) {
        return goodsClient.createGoodsInfo(createGoodsInfoReqVO);
    }

    /**
     * 更新商品信息
     *
     * @param updateGoodsInfoReqVO
     * @return
     */
    @Override
    public CommonResult<Void> updateGoodsInfo(UpdateGoodsInfoReqVO updateGoodsInfoReqVO) {
        return goodsClient.updateGoodsInfo(updateGoodsInfoReqVO);
    }

    /**
     * 删除商品信息
     *
     * @param goodsId
     * @return
     */
    @Override
    public CommonResult<Void> deleteGoodsInfo(Long goodsId) {
        return goodsClient.deleteGoodsInfo(goodsId);
    }

    /**
     * 获取商品信息详情
     *
     * @param goodsId
     * @return
     */
    @Override
    public CommonResult<GoodsInfoDetailsResVO> getGoodsInfoDetailsById(Long goodsId) {
        return goodsClient.getGoodsInfoDetailsById(goodsId);
    }

    /**
     * 更新商品状态
     *
     * @param updateGoodsInfoStatusReqVO
     * @return
     */
    @Override
    public CommonResult<Void> updateGoodsInfoStatus(UpdateGoodsInfoStatusReqVO updateGoodsInfoStatusReqVO) {
        return goodsClient.updateGoodsInfoStatus(updateGoodsInfoStatusReqVO);
    }

    /**
     * 商品分页查询
     *
     * @param queryGoodsInfoReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<GoodsInfoPageResVO>> queryGoodsInfoPage(QueryGoodsInfoReqVO queryGoodsInfoReqVO) {
        return goodsClient.queryGoodsInfoPage(queryGoodsInfoReqVO);
    }

    /**
     * 批量删除
     *
     * @param deleteBatchGoodsReqVO
     * @return
     */
    @Override
    public CommonResult<Void> batchDeleteGoodsInfos(DeleteBatchGoodsReqVO deleteBatchGoodsReqVO) {
        return goodsClient.batchDeleteGoodsInfos(deleteBatchGoodsReqVO);
    }
}
