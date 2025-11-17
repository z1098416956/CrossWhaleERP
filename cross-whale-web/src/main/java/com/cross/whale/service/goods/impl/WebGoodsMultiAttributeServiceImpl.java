package com.cross.whale.service.goods.impl;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.feign.goods.GoodsClient;
import com.cross.whale.req.CreateGoodsMultiAttributeReqVO;
import com.cross.whale.req.DeleteGoodsMultiAttributeReqVO;
import com.cross.whale.req.QueryGoodsMultiAttributeReqVO;
import com.cross.whale.req.UpdateGoodsMultiAttributeReqVO;
import com.cross.whale.res.GoodsMultiAttributeResVO;
import com.cross.whale.service.goods.WebGoodsMultiAttributeService;
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
