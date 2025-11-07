package com.neton.service.inventory;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.CreateGoodsMultiAttributeReqVO;
import com.neton.req.DeleteGoodsMultiAttributeReqVO;
import com.neton.req.QueryGoodsMultiAttributeReqVO;
import com.neton.req.UpdateGoodsMultiAttributeReqVO;
import com.neton.res.GoodsMultiAttributeResVO;

public interface WebGoodsMultiAttributeService {

    /**
     * 创建商品多属性
     * @param createMultiAttributeVO
     * @return
     */
    CommonResult<Void> createGoodsMultiAttribute(CreateGoodsMultiAttributeReqVO createMultiAttributeVO);

    /**
     * 更新商品多属性
     * @param updateMultiAttributeVO
     * @return
     */
    CommonResult<Void> updateGoodsMultiAttribute(UpdateGoodsMultiAttributeReqVO updateMultiAttributeVO);


    /**
     * 批量删除多属性
     */
    CommonResult<Void> deleteGoodsMultiAttribute(DeleteGoodsMultiAttributeReqVO deleteMultiAttributeVO);


    /**
     * 分页查询商品多属性
     * @param queryMultiAttributeVO
     * @return
     */
    CommonResult<PageUtil<GoodsMultiAttributeResVO>> queryGoodsMultiAttributePage(QueryGoodsMultiAttributeReqVO queryMultiAttributeVO);


    /**
     * 根据ID查询商品多属性
     * @param id
     * @return
     */
    CommonResult<GoodsMultiAttributeResVO> getGoodsMultiAttributeById(Long id);


    /**
     * 根据属性id删除商品多属性
     * @param id
     * @return
     */
    CommonResult<Void> deleteGoodsMultiAttributeByAttributeId(Long id);
}
