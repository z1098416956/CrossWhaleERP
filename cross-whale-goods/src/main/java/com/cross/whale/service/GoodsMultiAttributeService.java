package com.cross.whale.service;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateGoodsMultiAttributeReqVO;
import com.cross.whale.req.DeleteGoodsMultiAttributeReqVO;
import com.cross.whale.req.QueryGoodsMultiAttributeReqVO;
import com.cross.whale.req.UpdateGoodsMultiAttributeReqVO;
import com.cross.whale.res.GoodsMultiAttributeResVO;

public interface GoodsMultiAttributeService {

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
