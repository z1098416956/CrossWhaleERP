package com.neton.service.goods.impl;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.goods.GoodsClient;
import com.neton.req.CreateGoodsInfoReqVO;
import com.neton.req.QueryGoodsInfoReqVO;
import com.neton.req.UpdateGoodsInfoReqVO;
import com.neton.req.UpdateGoodsInfoStatusReqVO;
import com.neton.res.GoodsInfoDetailsResVO;
import com.neton.res.GoodsInfoPageResVO;
import com.neton.service.goods.WebGoodsInfoService;
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
}
