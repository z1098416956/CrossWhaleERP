package com.neton.service.goods.impl;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.goods.GoodsClient;
import com.neton.req.CreateGoodsMultiAttributeReqVO;
import com.neton.req.DeleteGoodsMultiAttributeReqVO;
import com.neton.req.QueryGoodsMultiAttributeReqVO;
import com.neton.req.UpdateGoodsMultiAttributeReqVO;
import com.neton.res.GoodsMultiAttributeResVO;
import com.neton.service.goods.WebGoodsMultiAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebGoodsMultiAttributeServiceImpl implements WebGoodsMultiAttributeService {

    @Autowired
    private GoodsClient goodsClient;
    /**
     * 创建商品多属性
     *
     * @param createMultiAttributeVO
     * @return
     */
    @Override
    public CommonResult<Void> createGoodsMultiAttribute(CreateGoodsMultiAttributeReqVO createMultiAttributeVO) {
        return goodsClient.createGoodsMultiAttribute(createMultiAttributeVO);
    }

    /**
     * 更新商品多属性
     *
     * @param updateMultiAttributeVO
     * @return
     */
    @Override
    public CommonResult<Void> updateGoodsMultiAttribute(UpdateGoodsMultiAttributeReqVO updateMultiAttributeVO) {
        return goodsClient.updateGoodsMultiAttribute(updateMultiAttributeVO);
    }

    /**
     * 批量删除多属性
     *
     * @param deleteMultiAttributeVO
     */
    @Override
    public CommonResult<Void> deleteGoodsMultiAttribute(DeleteGoodsMultiAttributeReqVO deleteMultiAttributeVO) {
        return goodsClient.deleteGoodsMultiAttribute(deleteMultiAttributeVO);
    }

    /**
     * 分页查询商品多属性
     *
     * @param queryMultiAttributeVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<GoodsMultiAttributeResVO>> queryGoodsMultiAttributePage(QueryGoodsMultiAttributeReqVO queryMultiAttributeVO) {
        return goodsClient.queryGoodsMultiAttributePage(queryMultiAttributeVO);
    }

    /**
     * 根据ID查询商品多属性
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<GoodsMultiAttributeResVO> getGoodsMultiAttributeById(Long id) {
        return goodsClient.getGoodsMultiAttributeById(id);
    }

    /**
     * 根据属性id删除商品多属性
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<Void> deleteGoodsMultiAttributeByAttributeId(Long id) {
        return goodsClient.deleteGoodsMultiAttributeByAttributeId(id);
    }
}
