package com.neton.service;


import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.CreateUnitVO;
import com.neton.req.QueryUnitReqVO;
import com.neton.req.UpdateUnitReqVO;
import com.neton.req.UpdateUnitStatusReqVO;
import com.neton.res.UnitPageResVO;
import com.neton.res.UnitResVO;

public interface GoodsBaseUnitService {

    /**
     * 创建基本单位副单位
     * @param createUnitVO
     * @return
     */
    CommonResult<Void> createGoodsBaseUnitInfo(CreateUnitVO createUnitVO);

    /**
     * 分页查询基本单位副单位
     * @param queryUnitReqVO
     * @return
     */
    CommonResult<PageUtil<UnitResVO>> getGoodsBaseUnitPage(QueryUnitReqVO queryUnitReqVO);

    /**
     * 分页查询基本单位副单位
     * @param queryUnitReqVO
     * @return
     */
    CommonResult<PageUtil<UnitPageResVO>> queryGoodsBaseUnitPage(QueryUnitReqVO queryUnitReqVO);

    /**
     * 根据ID查询基本单位副单位
     * @param id
     * @return
     */
    CommonResult<UnitResVO> getGoodsBaseUnitById(Long id);

    /**
     * 更新基本单位副单位
     * @param updateUnitVO
     * @return
     */
    CommonResult<Void> updateGoodsBaseUnitInfo(UpdateUnitReqVO updateUnitVO);

    /**
     * 删除基本单位副单位
     * @param id
     * @return
     */
    CommonResult<Void> deleteGoodsBaseUnitInfo(Long id);


    /**
     * 批量根据类型删除、禁用、启用
     * @param updateUnitStatusReqVO
     * @return
     */
    CommonResult<Void> updateGoodsBaseUnitByType(UpdateUnitStatusReqVO updateUnitStatusReqVO);
}
