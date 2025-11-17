package com.cross.whale.controller;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateGoodsMultiAttributeReqVO;
import com.cross.whale.req.DeleteGoodsMultiAttributeReqVO;
import com.cross.whale.req.QueryGoodsMultiAttributeReqVO;
import com.cross.whale.req.UpdateGoodsMultiAttributeReqVO;
import com.cross.whale.res.GoodsMultiAttributeResVO;
import com.cross.whale.service.GoodsMultiAttributeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/goods/multi/attribute")
public class GoodsMultiAttributeController {

    @Autowired
    private GoodsMultiAttributeService goodsMultiAttributeService;


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
    @DeleteMapping("/deleteGoodsMultiAttribute")
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
