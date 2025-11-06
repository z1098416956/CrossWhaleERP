package com.neton.service;

import cn.hutool.db.PageResult;
import com.neton.common.CommonResult;
import com.neton.req.CreateUnitVO;
import com.neton.req.QueryUnitReqVO;
import com.neton.req.UpdateUnitReqVO;
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
    CommonResult<PageResult<UnitResVO>> getGoodsBaseUnitPage(QueryUnitReqVO queryUnitReqVO);

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
}
