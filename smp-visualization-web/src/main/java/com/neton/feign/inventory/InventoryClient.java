package com.neton.feign.inventory;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.FeignConfig;
import com.neton.req.*;
import com.neton.res.ProductTypeDetailsVO;
import com.neton.res.TreeNodeVO;
import com.neton.res.UnitPageResVO;
import com.neton.res.UnitResVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "smp-inventory-service" ,configuration = FeignConfig.class,contextId = "inventory-service")
public interface InventoryClient {


    /**
     * 创建产品类型
     * @param createProductTypeVO
     * @return
     */
    @PostMapping("/v1/product/type/createProductTypeInfo")
    public CommonResult createProductTypeInfo(@RequestBody CreateProductTypeVO createProductTypeVO);

    /**
     * 更新产品类型
     * @param updateProductTypeVO
     * @return
     */
    @PostMapping("/v1/product/type/updateProductTypeInfo")
    public CommonResult updateProductTypeInfo(@RequestBody UpdateProductTypeVO updateProductTypeVO);

    /**
     * 获取分类详情
     * @param id
     * @return
     */
    @GetMapping("/v1/product/type/getProductTypeDetails")
    public CommonResult<ProductTypeDetailsVO> getProductTypeDetails(@RequestParam Long id);


    /**
     * 删除分类详情
     * @param id
     * @return
     */
    @GetMapping("/v1/product/type/deleteProductTypeInfo")
    public CommonResult deleteProductTypeInfo(@RequestParam Long id);


    /**
     *
     * @return
     */
    @GetMapping("/v1/product/type/getProductTypeList")
    public CommonResult<List<TreeNodeVO>> getProductTypeList();

    /**
     * 分类列表
     * @param queryProductTypeVO
     * @return
     */
    @PostMapping("/v1/product/type/queryProductTypePage")
    public CommonResult<PageUtil<TreeNodeVO>> queryProductTypePage(@RequestBody QueryProductTypeVO queryProductTypeVO);

    /**
     * 删除分类
     * @param deleteProductTypeVO
     * @return
     */
    @PostMapping("/v1/product/type/deleteProductTypeIds")
    public CommonResult deleteProductTypeIds(@RequestBody DeleteProductTypeVO deleteProductTypeVO);



    /**
     * 创建基本单位副单位
     * @param createUnitVO
     * @return
     */
    @PostMapping("/v1/good/base/unit/createGoodsBaseUnitInfo")
    public CommonResult<Void> createGoodsBaseUnitInfo(@RequestBody CreateUnitVO createUnitVO);

    /**
     * 分页查询基本单位副单位
     * @param queryUnitReqVO
     * @return
     */
    @PostMapping("/v1/good/base/unit/getGoodsBaseUnitPage")
    public CommonResult<PageUtil<UnitResVO>> getGoodsBaseUnitPage(@RequestBody QueryUnitReqVO queryUnitReqVO);

    /**
     * 根据ID查询基本单位副单位
     * @param id
     * @return
     */
    @GetMapping("/v1/good/base/unit/getGoodsBaseUnitById")
    public CommonResult<UnitResVO> getGoodsBaseUnitById(@RequestParam("id") Long id);
    /**
     * 更新基本单位副单位
     * @param updateUnitVO
     * @return
     */
    @PostMapping("/v1/good/base/unit/updateGoodsBaseUnitInfo")
    public CommonResult<Void> updateGoodsBaseUnitInfo(@RequestBody UpdateUnitReqVO updateUnitVO);

    /**
     * 删除基本单位副单位
     * @param id
     * @return
     */
    @DeleteMapping("/v1/good/base/unit/deleteGoodsBaseUnitInfo")
    public CommonResult<Void> deleteGoodsBaseUnitInfo(@RequestParam("id") Long id);

    /**
     * 分页查询基本单位副单位
     * @param queryUnitReqVO
     * @return
     */
    @PostMapping("/v1/good/base/unit/queryGoodsBaseUnitPage")
    public CommonResult<PageUtil<UnitPageResVO>> queryGoodsBaseUnitPage(@RequestBody QueryUnitReqVO queryUnitReqVO);


    /**
     * 批量根据类型删除、禁用、启用
     * @param updateUnitStatusReqVO
     * @return
     */
    @PostMapping("/v1/good/base/unit/updateGoodsBaseUnitByType")
    public CommonResult<Void> updateGoodsBaseUnitByType(@RequestBody UpdateUnitStatusReqVO updateUnitStatusReqVO);
}
