package com.cross.whale.controller.goods;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateUnitVO;
import com.cross.whale.req.QueryUnitReqVO;
import com.cross.whale.req.UpdateUnitReqVO;
import com.cross.whale.req.UpdateUnitStatusReqVO;
import com.cross.whale.res.UnitPageResVO;
import com.cross.whale.res.UnitResVO;
import com.cross.whale.service.goods.WebGoodsBaseUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/v1/good/base/unit")
public class WebGoodsBaseUnitController {

    @Autowired
    private WebGoodsBaseUnitService goodsBaseUnitService;

    /**
     * 创建基本单位副单位
     * @param createUnitVO
     * @return
     */
    @PostMapping("/createGoodsBaseUnitInfo")
    public CommonResult<Void> createGoodsBaseUnitInfo(@RequestBody CreateUnitVO createUnitVO) {
        return goodsBaseUnitService.createGoodsBaseUnitInfo(createUnitVO);
    }

    /**
     * 分页查询基本单位副单位
     * @param queryUnitReqVO
     * @return
     */
    @PostMapping("/getGoodsBaseUnitPage")
    public CommonResult<PageUtil<UnitResVO>> getGoodsBaseUnitPage(@RequestBody QueryUnitReqVO queryUnitReqVO) {
        return goodsBaseUnitService.getGoodsBaseUnitPage(queryUnitReqVO);
    }

    /**
     * 根据ID查询基本单位副单位
     * @param id
     * @return
     */
    @GetMapping("/getGoodsBaseUnitById")
    public CommonResult<UnitResVO> getGoodsBaseUnitById(@RequestParam("id") Long id) {
        return goodsBaseUnitService.getGoodsBaseUnitById(id);
    }

    /**
     * 更新基本单位副单位
     * @param updateUnitVO
     * @return
     */
    @PostMapping("/updateGoodsBaseUnitInfo")
    public CommonResult<Void> updateGoodsBaseUnitInfo(@RequestBody UpdateUnitReqVO updateUnitVO) {
        return goodsBaseUnitService.updateGoodsBaseUnitInfo(updateUnitVO);
    }

    /**
     * 删除基本单位副单位
     * @param id
     * @return
     */
    @DeleteMapping("/deleteGoodsBaseUnitInfo")
    public CommonResult<Void> deleteGoodsBaseUnitInfo(@RequestParam("id") Long id) {
        return goodsBaseUnitService.deleteGoodsBaseUnitInfo(id);
    }

    /**
     * 分页查询基本单位副单位
     * @param queryUnitReqVO
     * @return
     */
    @PostMapping("/queryGoodsBaseUnitPage")
    public CommonResult<PageUtil<UnitPageResVO>> queryGoodsBaseUnitPage(@RequestBody QueryUnitReqVO queryUnitReqVO){
        return goodsBaseUnitService.queryGoodsBaseUnitPage(queryUnitReqVO);
    }

    /**
     * 批量根据类型删除、禁用、启用
     * @param updateUnitStatusReqVO
     * @return
     */
    @PostMapping("/updateGoodsBaseUnitByType")
    public CommonResult<Void> updateGoodsBaseUnitByType(@RequestBody UpdateUnitStatusReqVO updateUnitStatusReqVO){
        return goodsBaseUnitService.updateGoodsBaseUnitByType(updateUnitStatusReqVO);
    }

    /**
     * 分页查询基本单位副单位
     * @param queryUnitReqVO
     * @return
     */
    @PostMapping("/queryGoodsBaseUnitPage2")
    public CommonResult<PageUtil<UnitPageResVO>> queryGoodsBaseUnitPage2(@RequestBody QueryUnitReqVO queryUnitReqVO){
        return goodsBaseUnitService.queryGoodsBaseUnitPage2(queryUnitReqVO);
    }
}
