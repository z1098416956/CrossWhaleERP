package com.neton.controller.purchase;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.*;
import com.neton.res.ReceiptsInfoPageResVO;
import com.neton.res.ReceiptsInfoResVO;
import com.neton.service.purchase.WebReceiptsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web/v1/receipts/info")
public class WebReceiptsInfoController {

    @Autowired
    private WebReceiptsInfoService webReceiptsInfoService;

    /**
     * 添加请购单
     * @param createReceiptsInfoReqVO
     * @return
     */
    @RequestMapping(value = "/createReceiptsInfo",method = RequestMethod.POST)
    public CommonResult<Void> createReceiptsInfo(@RequestBody CreateReceiptsInfoReqVO createReceiptsInfoReqVO){
        return webReceiptsInfoService.createReceiptsInfo(createReceiptsInfoReqVO);
    }


    /**
     * 获取请购单详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getReceiptsDetailsInfo",method = RequestMethod.GET)
    public CommonResult<ReceiptsInfoResVO> getReceiptsDetailsInfo(@RequestParam Long id){
        return webReceiptsInfoService.getReceiptsDetailsInfo(id);
    }


    /**
     * 删除请购单
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteReceiptsInfo",method = RequestMethod.DELETE)
    public CommonResult<Void> deleteReceiptsInfo(@RequestParam Long id){
        return webReceiptsInfoService.deleteReceiptsInfo(id);
    }

    /**
     * 更新请购单状态
     * @param updateReceiptsStatusReqVO
     * @return
     */
    @RequestMapping(value = "/updateReceiptsStatus",method = RequestMethod.PUT)
    public CommonResult<Void> updateReceiptsStatus(@RequestBody UpdateReceiptsStatusReqVO updateReceiptsStatusReqVO){
        return webReceiptsInfoService.updateReceiptsStatus(updateReceiptsStatusReqVO);
    }


    /**
     *删除单据
     * @param deleteReceiptsReqVO
     * @return
     */
    @RequestMapping(value = "/deleteReceipts",method = RequestMethod.DELETE)
    public CommonResult<Void> deleteReceipts(@RequestBody DeleteReceiptsReqVO deleteReceiptsReqVO){
        return webReceiptsInfoService.deleteReceipts(deleteReceiptsReqVO);
    }


    /**
     * 请购单列表
     * @param queryReceiptsReqVO
     * @return
     */
    @RequestMapping(value = "/queryReceiptsPage",method = RequestMethod.POST)
    public CommonResult<PageUtil<ReceiptsInfoPageResVO>> queryReceiptsPage(@RequestBody QueryReceiptsReqVO queryReceiptsReqVO){
        return webReceiptsInfoService.queryReceiptsPage(queryReceiptsReqVO);
    }

    /**
     * 更新单据信息
     * @param updateReceiptsInfoReqVO
     * @return
     */
    @RequestMapping(value = "/updateReceiptsInfo",method = RequestMethod.PUT)
    public CommonResult<Void> updateReceiptsInfo(@RequestBody UpdateReceiptsInfoReqVO updateReceiptsInfoReqVO){
        return webReceiptsInfoService.updateReceiptsInfo(updateReceiptsInfoReqVO);
    }
}
