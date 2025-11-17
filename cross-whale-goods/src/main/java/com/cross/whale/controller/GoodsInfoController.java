package com.cross.whale.controller;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.*;
import com.cross.whale.res.GoodsInfoDetailsResVO;
import com.cross.whale.res.GoodsInfoPageResVO;
import com.cross.whale.res.GoodsInfoReceiptsPageResVO;
import com.cross.whale.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/goods/info")
public class GoodsInfoController {

    @Autowired
    private GoodsInfoService goodsInfoService;


    /**
     * 创建商品信息
     * @param createGoodsInfoReqVO
     * @return
     */
    @PostMapping("/createGoodsInfo")
    public CommonResult<Void> createGoodsInfo(@RequestBody CreateGoodsInfoReqVO createGoodsInfoReqVO){

        return goodsInfoService.createGoodsInfo(createGoodsInfoReqVO);
    }



    /**
     * 删除商品信息
     * @param goodsId
     * @return
     */
    @DeleteMapping("/deleteGoodsInfo")
    public CommonResult<Void> deleteGoodsInfo(@RequestParam("goodsId") Long goodsId){
        return goodsInfoService.deleteGoodsInfo(goodsId);
    }

    /**
     * 批量删除
     * @param deleteBatchGoodsReqVO
     * @return
     */
    @DeleteMapping("/batchDeleteGoodsInfos")
    public CommonResult<Void> batchDeleteGoodsInfos(@RequestBody DeleteBatchGoodsReqVO deleteBatchGoodsReqVO){
        return goodsInfoService.batchDeleteGoodsInfos(deleteBatchGoodsReqVO);
    }
    /**
     * 更新商品信息
     * @param updateGoodsInfoReqVO
     * @return
     */
    @PutMapping("/updateGoodsInfo")
    public CommonResult<Void> updateGoodsInfo(@RequestBody UpdateGoodsInfoReqVO updateGoodsInfoReqVO){
        return goodsInfoService.updateGoodsInfo(updateGoodsInfoReqVO);
    }



    /**
     * 获取商品信息详情
     * @param goodsId
     * @return
     */
    @GetMapping("/getGoodsInfoDetailsById")
    public CommonResult<GoodsInfoDetailsResVO> getGoodsInfoDetailsById(@RequestParam("goodsId") Long goodsId){
        return goodsInfoService.getGoodsInfoDetailsById(goodsId);
    }



    /**
     * 更新商品状态
     * @param updateGoodsInfoStatusReqVO
     * @return
     */
    @PutMapping("/updateGoodsInfoStatus")
    public CommonResult<Void> updateGoodsInfoStatus(@RequestBody UpdateGoodsInfoStatusReqVO updateGoodsInfoStatusReqVO){
        return goodsInfoService.updateGoodsInfoStatus(updateGoodsInfoStatusReqVO);
    }



    /**
     * 商品分页查询
     * @param queryGoodsInfoReqVO
     * @return
     */
    @PostMapping("/queryGoodsInfoPage")
    public CommonResult<PageUtil<GoodsInfoPageResVO>> queryGoodsInfoPage(@RequestBody QueryGoodsInfoReqVO queryGoodsInfoReqVO){
        return goodsInfoService.queryGoodsInfoPage(queryGoodsInfoReqVO);
    }

    /**
     * 请购单查询商品
     * @param queryGoodsInfoReqVO
     * @return
     */
    @PostMapping("/queryGoodsReceiptsPage")
    public CommonResult<PageUtil<GoodsInfoReceiptsPageResVO>> queryGoodsReceiptsPage(@RequestBody QueryGoodsInfoReqVO queryGoodsInfoReqVO){
        return goodsInfoService.queryGoodsReceiptsPage(queryGoodsInfoReqVO);
    }
}
