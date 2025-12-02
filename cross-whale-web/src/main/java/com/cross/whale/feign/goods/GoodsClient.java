package com.cross.whale.feign.goods;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.feign.FeignConfig;
import com.cross.whale.req.*;
import com.cross.whale.res.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cross-whale-goods" ,configuration = FeignConfig.class,contextId = "goods-service")
public interface GoodsClient {

    /**
     * 创建基本单位副单位
     *
     * @param createUnitVO
     * @return
     */
    @PostMapping("/v1/good/base/unit/createGoodsBaseUnitInfo")
    public CommonResult<Void> createGoodsBaseUnitInfo(@RequestBody CreateUnitVO createUnitVO);

    /**
     * 分页查询基本单位副单位
     *
     * @param queryUnitReqVO
     * @return
     */
    @PostMapping("/v1/good/base/unit/getGoodsBaseUnitPage")
    public CommonResult<PageUtil<UnitResVO>> getGoodsBaseUnitPage(@RequestBody QueryUnitReqVO queryUnitReqVO);

    /**
     * 根据ID查询基本单位副单位
     *
     * @param id
     * @return
     */
    @GetMapping("/v1/good/base/unit/getGoodsBaseUnitById")
    public CommonResult<UnitResVO> getGoodsBaseUnitById(@RequestParam("id") Long id);

    /**
     * 更新基本单位副单位
     *
     * @param updateUnitVO
     * @return
     */
    @PostMapping("/v1/good/base/unit/updateGoodsBaseUnitInfo")
    public CommonResult<Void> updateGoodsBaseUnitInfo(@RequestBody UpdateUnitReqVO updateUnitVO);

    /**
     * 删除基本单位副单位
     *
     * @param id
     * @return
     */
    @DeleteMapping("/v1/good/base/unit/deleteGoodsBaseUnitInfo")
    public CommonResult<Void> deleteGoodsBaseUnitInfo(@RequestParam("id") Long id);

    /**
     * 分页查询基本单位副单位
     *
     * @param queryUnitReqVO
     * @return
     */
    @PostMapping("/v1/good/base/unit/queryGoodsBaseUnitPage")
    public CommonResult<PageUtil<UnitPageResVO>> queryGoodsBaseUnitPage(@RequestBody QueryUnitReqVO queryUnitReqVO);


    /**
     * 批量根据类型删除、禁用、启用
     *
     * @param updateUnitStatusReqVO
     * @return
     */
    @PostMapping("/v1/good/base/unit/updateGoodsBaseUnitByType")
    public CommonResult<Void> updateGoodsBaseUnitByType(@RequestBody UpdateUnitStatusReqVO updateUnitStatusReqVO);


    /**
     * 创建商品多属性
     *
     * @param createMultiAttributeVO
     * @return
     */
    @PostMapping("/v1/goods/multi/attribute/createGoodsMultiAttribute")
    public CommonResult<Void> createGoodsMultiAttribute(@RequestBody CreateGoodsMultiAttributeReqVO createMultiAttributeVO);

    /**
     * 更新商品多属性
     *
     * @param updateMultiAttributeVO
     * @return
     */
    @PostMapping("/v1/goods/multi/attribute/updateGoodsMultiAttribute")
    public CommonResult<Void> updateGoodsMultiAttribute(@RequestBody UpdateGoodsMultiAttributeReqVO updateMultiAttributeVO);

    /**
     * 批量删除多属性
     */
    @DeleteMapping("/v1/goods/multi/attribute/deleteGoodsMultiAttribute")
    public CommonResult<Void> deleteGoodsMultiAttribute(@RequestBody DeleteGoodsMultiAttributeReqVO deleteMultiAttributeVO);


    /**
     * 分页查询商品多属性
     *
     * @param queryMultiAttributeVO
     * @return
     */
    @PostMapping("/v1/goods/multi/attribute/queryGoodsMultiAttributePage")
    public CommonResult<PageUtil<GoodsMultiAttributeResVO>> queryGoodsMultiAttributePage(@RequestBody QueryGoodsMultiAttributeReqVO queryMultiAttributeVO);

    /**
     * 根据ID查询商品多属性
     *
     * @param id
     * @return
     */
    @GetMapping("/v1/goods/multi/attribute/getGoodsMultiAttributeById")
    public CommonResult<GoodsMultiAttributeResVO> getGoodsMultiAttributeById(@RequestParam("id") Long id);


    /**
     * 根据属性id删除商品多属性
     *
     * @param id
     * @return
     */
    @DeleteMapping("/v1/goods/multi/attribute/deleteGoodsMultiAttributeByAttributeId")
    public CommonResult<Void> deleteGoodsMultiAttributeByAttributeId(@RequestParam("id") Long id);


    /**
     * 创建商品信息
     *
     * @param createGoodsInfoReqVO
     * @return
     */
    @PostMapping("/v1/goods/info/createGoodsInfo")
    public CommonResult<Void> createGoodsInfo(@RequestBody CreateGoodsInfoReqVO createGoodsInfoReqVO);


    /**
     * 删除商品信息
     *
     * @param goodsId
     * @return
     */
    @DeleteMapping("/v1/goods/info/deleteGoodsInfo")
    public CommonResult<Void> deleteGoodsInfo(@RequestParam("goodsId") Long goodsId);

    /**
     * 更新商品信息
     *
     * @param updateGoodsInfoReqVO
     * @return
     */
    @PutMapping("/v1/goods/info/updateGoodsInfo")
    public CommonResult<Void> updateGoodsInfo(@RequestBody UpdateGoodsInfoReqVO updateGoodsInfoReqVO);


    /**
     * 获取商品信息详情
     *
     * @param goodsId
     * @return
     */
    @GetMapping("/v1/goods/info/getGoodsInfoDetailsById")
    public CommonResult<GoodsInfoDetailsResVO> getGoodsInfoDetailsById(@RequestParam("goodsId") Long goodsId);


    /**
     * 更新商品状态
     *
     * @param updateGoodsInfoStatusReqVO
     * @return
     */
    @PutMapping("/v1/goods/info/updateGoodsInfoStatus")
    public CommonResult<Void> updateGoodsInfoStatus(@RequestBody UpdateGoodsInfoStatusReqVO updateGoodsInfoStatusReqVO);


    /**
     * 商品分页查询
     *
     * @param queryGoodsInfoReqVO
     * @return
     */
    @PostMapping("/v1/goods/info/queryGoodsInfoPage")
    public CommonResult<PageUtil<GoodsInfoPageResVO>> queryGoodsInfoPage(@RequestBody QueryGoodsInfoReqVO queryGoodsInfoReqVO);


    /**
     * 批量删除
     * @param deleteBatchGoodsReqVO
     * @return
     */
    @DeleteMapping("/v1/goods/info/batchDeleteGoodsInfos")
    public CommonResult<Void> batchDeleteGoodsInfos(@RequestBody DeleteBatchGoodsReqVO deleteBatchGoodsReqVO);


    /**
     * 分页查询基本单位副单位
     * @param queryUnitReqVO
     * @return
     */
    @PostMapping("/v1/good/base/unit/queryGoodsBaseUnitPage2")
    public CommonResult<PageUtil<UnitPageResVO>> queryGoodsBaseUnitPage2(@RequestBody QueryUnitReqVO queryUnitReqVO);

}
