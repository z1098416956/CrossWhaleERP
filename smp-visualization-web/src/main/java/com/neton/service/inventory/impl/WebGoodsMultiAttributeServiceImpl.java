package com.neton.service.inventory.impl;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.inventory.InventoryClient;
import com.neton.req.CreateGoodsMultiAttributeReqVO;
import com.neton.req.DeleteGoodsMultiAttributeReqVO;
import com.neton.req.QueryGoodsMultiAttributeReqVO;
import com.neton.req.UpdateGoodsMultiAttributeReqVO;
import com.neton.res.GoodsMultiAttributeResVO;
import com.neton.service.inventory.WebGoodsMultiAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebGoodsMultiAttributeServiceImpl implements WebGoodsMultiAttributeService {

    @Autowired
    private InventoryClient inventoryClient;
    /**
     * 创建商品多属性
     *
     * @param createMultiAttributeVO
     * @return
     */
    @Override
    public CommonResult<Void> createGoodsMultiAttribute(CreateGoodsMultiAttributeReqVO createMultiAttributeVO) {
        return inventoryClient.createGoodsMultiAttribute(createMultiAttributeVO);
    }

    /**
     * 更新商品多属性
     *
     * @param updateMultiAttributeVO
     * @return
     */
    @Override
    public CommonResult<Void> updateGoodsMultiAttribute(UpdateGoodsMultiAttributeReqVO updateMultiAttributeVO) {
        return inventoryClient.updateGoodsMultiAttribute(updateMultiAttributeVO);
    }

    /**
     * 批量删除多属性
     *
     * @param deleteMultiAttributeVO
     */
    @Override
    public CommonResult<Void> deleteGoodsMultiAttribute(DeleteGoodsMultiAttributeReqVO deleteMultiAttributeVO) {
        return inventoryClient.deleteGoodsMultiAttribute(deleteMultiAttributeVO);
    }

    /**
     * 分页查询商品多属性
     *
     * @param queryMultiAttributeVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<GoodsMultiAttributeResVO>> queryGoodsMultiAttributePage(QueryGoodsMultiAttributeReqVO queryMultiAttributeVO) {
        return inventoryClient.queryGoodsMultiAttributePage(queryMultiAttributeVO);
    }

    /**
     * 根据ID查询商品多属性
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<GoodsMultiAttributeResVO> getGoodsMultiAttributeById(Long id) {
        return inventoryClient.getGoodsMultiAttributeById(id);
    }

    /**
     * 根据属性id删除商品多属性
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<Void> deleteGoodsMultiAttributeByAttributeId(Long id) {
        return inventoryClient.deleteGoodsMultiAttributeByAttributeId(id);
    }
}
