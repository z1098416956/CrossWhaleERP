package com.neton.service.goods.impl;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.goods.GoodsClient;
import com.neton.req.CreateUnitVO;
import com.neton.req.QueryUnitReqVO;
import com.neton.req.UpdateUnitReqVO;
import com.neton.req.UpdateUnitStatusReqVO;
import com.neton.res.UnitPageResVO;
import com.neton.res.UnitResVO;
import com.neton.service.goods.WebGoodsBaseUnitService;
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
}
