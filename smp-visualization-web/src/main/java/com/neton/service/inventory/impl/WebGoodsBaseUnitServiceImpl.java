package com.neton.service.inventory.impl;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.inventory.InventoryClient;
import com.neton.req.CreateUnitVO;
import com.neton.req.QueryUnitReqVO;
import com.neton.req.UpdateUnitReqVO;
import com.neton.res.UnitResVO;
import com.neton.service.inventory.WebGoodsBaseUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebGoodsBaseUnitServiceImpl implements WebGoodsBaseUnitService {

    @Autowired
    private InventoryClient inventoryClient;
    /**
     * 创建基本单位副单位
     *
     * @param createUnitVO
     * @return
     */
    @Override
    public CommonResult<Void> createGoodsBaseUnitInfo(CreateUnitVO createUnitVO) {
        return inventoryClient.createGoodsBaseUnitInfo(createUnitVO);
    }

    /**
     * 分页查询基本单位副单位
     *
     * @param queryUnitReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<UnitResVO>> getGoodsBaseUnitPage(QueryUnitReqVO queryUnitReqVO) {
        return inventoryClient.getGoodsBaseUnitPage(queryUnitReqVO);
    }

    /**
     * 根据ID查询基本单位副单位
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<UnitResVO> getGoodsBaseUnitById(Long id) {
        return inventoryClient.getGoodsBaseUnitById(id);
    }

    /**
     * 更新基本单位副单位
     *
     * @param updateUnitVO
     * @return
     */
    @Override
    public CommonResult<Void> updateGoodsBaseUnitInfo(UpdateUnitReqVO updateUnitVO) {
        return inventoryClient.updateGoodsBaseUnitInfo(updateUnitVO);
    }

    /**
     * 删除基本单位副单位
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<Void> deleteGoodsBaseUnitInfo(Long id) {
        return inventoryClient.deleteGoodsBaseUnitInfo(id);
    }
}
