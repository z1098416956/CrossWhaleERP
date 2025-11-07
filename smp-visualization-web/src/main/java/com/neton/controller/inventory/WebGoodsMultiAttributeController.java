package com.neton.controller.inventory;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.CreateGoodsMultiAttributeReqVO;
import com.neton.req.DeleteGoodsMultiAttributeReqVO;
import com.neton.req.QueryGoodsMultiAttributeReqVO;
import com.neton.req.UpdateGoodsMultiAttributeReqVO;
import com.neton.res.GoodsMultiAttributeResVO;
import com.neton.service.inventory.WebGoodsMultiAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/v1/goods/multi/attribute")
public class WebGoodsMultiAttributeController {

    @Autowired
    private WebGoodsMultiAttributeService goodsMultiAttributeService;


    /**
     * 创建商品多属性
     *
     * @param createMultiAttributeVO
     * @return
     */
    @PostMapping("/createGoodsMultiAttribute")
    public CommonResult<Void> createGoodsMultiAttribute(@RequestBody CreateGoodsMultiAttributeReqVO createMultiAttributeVO) {
        return goodsMultiAttributeService.createGoodsMultiAttribute(createMultiAttributeVO);
    }

    /**
     * 更新商品多属性
     *
     * @param updateMultiAttributeVO
     * @return
     */
    @PostMapping("/updateGoodsMultiAttribute")
    public CommonResult<Void> updateGoodsMultiAttribute(@RequestBody UpdateGoodsMultiAttributeReqVO updateMultiAttributeVO) {
        return goodsMultiAttributeService.updateGoodsMultiAttribute(updateMultiAttributeVO);
    }


    /**
     * 批量删除多属性
     */
    @DeleteMapping("/deleteBatchGoodsMultiAttribute")
    public CommonResult<Void> deleteGoodsMultiAttribute(@RequestBody DeleteGoodsMultiAttributeReqVO deleteMultiAttributeVO) {
        return goodsMultiAttributeService.deleteGoodsMultiAttribute(deleteMultiAttributeVO);
    }


    /**
     * 分页查询商品多属性
     *
     * @param queryMultiAttributeVO
     * @return
     */
    @PostMapping("/queryGoodsMultiAttributePage")
    public CommonResult<PageUtil<GoodsMultiAttributeResVO>> queryGoodsMultiAttributePage(@RequestBody QueryGoodsMultiAttributeReqVO queryMultiAttributeVO) {
        return goodsMultiAttributeService.queryGoodsMultiAttributePage(queryMultiAttributeVO);
    }


    /**
     * 根据ID查询商品多属性
     *
     * @param id
     * @return
     */
    @GetMapping("/getGoodsMultiAttributeById")
    public CommonResult<GoodsMultiAttributeResVO> getGoodsMultiAttributeById(@RequestParam("id") Long id) {
        return goodsMultiAttributeService.getGoodsMultiAttributeById(id);
    }


    /**
     * 根据属性id删除商品多属性
     *
     * @param id
     * @return
     */
    @DeleteMapping("/deleteGoodsMultiAttributeByAttributeId")
    public CommonResult<Void> deleteGoodsMultiAttributeByAttributeId(@RequestParam("id") Long id) {
        return goodsMultiAttributeService.deleteGoodsMultiAttributeByAttributeId(id);
    }
}
