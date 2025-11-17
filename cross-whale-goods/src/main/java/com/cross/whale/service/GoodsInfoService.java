package com.cross.whale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.entity.GoodsInfoDO;
import com.cross.whale.req.*;
import com.cross.whale.res.GoodsInfoDetailsResVO;
import com.cross.whale.res.GoodsInfoPageResVO;
import com.cross.whale.res.GoodsInfoReceiptsPageResVO;

public interface GoodsInfoService extends IService<GoodsInfoDO> {

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
     * 批量删除
     * @param deleteBatchGoodsReqVO
     * @return
     */
    CommonResult<Void> batchDeleteGoodsInfos(DeleteBatchGoodsReqVO deleteBatchGoodsReqVO);

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
     * 请购单查询商品
     * @param queryGoodsInfoReqVO
     * @return
     */
    CommonResult<PageUtil<GoodsInfoReceiptsPageResVO>> queryGoodsReceiptsPage(QueryGoodsInfoReqVO queryGoodsInfoReqVO);
}
