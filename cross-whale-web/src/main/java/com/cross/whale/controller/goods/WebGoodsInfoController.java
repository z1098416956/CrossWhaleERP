package com.cross.whale.controller.goods;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.*;
import com.cross.whale.res.GoodsInfoDetailsResVO;
import com.cross.whale.res.GoodsInfoPageResVO;
import com.cross.whale.service.goods.WebGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/v1/goods/info")
public class WebGoodsInfoController {

    @Autowired
    private WebGoodsInfoService webGoodsInfoService;

    /**
     * 创建商品信息
     * @param createGoodsInfoReqVO
     * @return
     */
    @PostMapping("/createGoodsInfo")
    public CommonResult<Void> createGoodsInfo(@RequestBody CreateGoodsInfoReqVO createGoodsInfoReqVO){

        return webGoodsInfoService.createGoodsInfo(createGoodsInfoReqVO);
    }



    /**
     * 删除商品信息
     * @param goodsId
     * @return
     */
    @DeleteMapping("/deleteGoodsInfo")
    public CommonResult<Void> deleteGoodsInfo(@RequestParam("goodsId") Long goodsId){
        return webGoodsInfoService.deleteGoodsInfo(goodsId);
    }

    /**
     * 更新商品信息
     * @param updateGoodsInfoReqVO
     * @return
     */
    @PutMapping("/updateGoodsInfo")
    public CommonResult<Void> updateGoodsInfo(@RequestBody UpdateGoodsInfoReqVO updateGoodsInfoReqVO){
        return webGoodsInfoService.updateGoodsInfo(updateGoodsInfoReqVO);
    }



    /**
     * 获取商品信息详情
     * @param id
     * @return
     */
    @GetMapping("/getGoodsInfoDetailsById")
    public CommonResult<GoodsInfoDetailsResVO> getGoodsInfoDetailsById(@RequestParam Long id){
        return webGoodsInfoService.getGoodsInfoDetailsById(id);
    }



    /**
     * 更新商品状态
     * @param updateGoodsInfoStatusReqVO
     * @return
     */
    @PutMapping("/updateGoodsInfoStatus")
    public CommonResult<Void> updateGoodsInfoStatus(@RequestBody UpdateGoodsInfoStatusReqVO updateGoodsInfoStatusReqVO){
        return webGoodsInfoService.updateGoodsInfoStatus(updateGoodsInfoStatusReqVO);
    }



    /**
     * 商品分页查询
     * @param queryGoodsInfoReqVO
     * @return
     */
    @PostMapping("/queryGoodsInfoPage")
    public CommonResult<PageUtil<GoodsInfoPageResVO>> queryGoodsInfoPage(@RequestBody QueryGoodsInfoReqVO queryGoodsInfoReqVO){
        return webGoodsInfoService.queryGoodsInfoPage(queryGoodsInfoReqVO);
    }

    /**
     * 批量删除
     * @param deleteBatchGoodsReqVO
     * @return
     */
    @DeleteMapping("/batchDeleteGoodsInfos")
    public CommonResult<Void> batchDeleteGoodsInfos(@RequestBody DeleteBatchGoodsReqVO deleteBatchGoodsReqVO){
        return webGoodsInfoService.batchDeleteGoodsInfos(deleteBatchGoodsReqVO);
    }
}
