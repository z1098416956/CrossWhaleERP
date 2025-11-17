package com.cross.whale.service.goods.impl;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.feign.goods.GoodsClient;
import com.cross.whale.req.CreateUnitVO;
import com.cross.whale.req.QueryUnitReqVO;
import com.cross.whale.req.UpdateUnitReqVO;
import com.cross.whale.req.UpdateUnitStatusReqVO;
import com.cross.whale.res.UnitPageResVO;
import com.cross.whale.res.UnitResVO;
import com.cross.whale.service.goods.WebGoodsBaseUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebGoodsBaseUnitServiceImpl implements WebGoodsBaseUnitService {

    @Autowired
    private GoodsClient goodsClient;
    /**
     * 创建基本单位副单位
     *
     * @param createUnitVO
     * @return
     */
    @Override
    public CommonResult<Void> createGoodsBaseUnitInfo(CreateUnitVO createUnitVO) {
        return goodsClient.createGoodsBaseUnitInfo(createUnitVO);
    }

    /**
     * 分页查询基本单位副单位
     *
     * @param queryUnitReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<UnitResVO>> getGoodsBaseUnitPage(QueryUnitReqVO queryUnitReqVO) {
        return goodsClient.getGoodsBaseUnitPage(queryUnitReqVO);
    }

    /**
     * 根据ID查询基本单位副单位
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<UnitResVO> getGoodsBaseUnitById(Long id) {
        return goodsClient.getGoodsBaseUnitById(id);
    }

    /**
     * 更新基本单位副单位
     *
     * @param updateUnitVO
     * @return
     */
    @Override
    public CommonResult<Void> updateGoodsBaseUnitInfo(UpdateUnitReqVO updateUnitVO) {
        return goodsClient.updateGoodsBaseUnitInfo(updateUnitVO);
    }

    /**
     * 删除基本单位副单位
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<Void> deleteGoodsBaseUnitInfo(Long id) {
        return goodsClient.deleteGoodsBaseUnitInfo(id);
    }

    /**
     * 分页查询基本单位副单位
     *
     * @param queryUnitReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<UnitPageResVO>> queryGoodsBaseUnitPage(QueryUnitReqVO queryUnitReqVO) {
        return goodsClient.queryGoodsBaseUnitPage(queryUnitReqVO);
    }

    /**
     * 批量根据类型删除、禁用、启用
     *
     * @param updateUnitStatusReqVO
     * @return
     */
    @Override
    public CommonResult<Void> updateGoodsBaseUnitByType(UpdateUnitStatusReqVO updateUnitStatusReqVO) {

        return goodsClient.updateGoodsBaseUnitByType(updateUnitStatusReqVO);
    }

    /**
     * 分页查询基本单位副单位
     *
     * @param queryUnitReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<UnitPageResVO>> queryGoodsBaseUnitPage2(QueryUnitReqVO queryUnitReqVO) {
        return goodsClient.queryGoodsBaseUnitPage2(queryUnitReqVO);
    }
}
