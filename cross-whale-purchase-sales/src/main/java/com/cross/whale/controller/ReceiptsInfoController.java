package com.cross.whale.controller;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.*;
import com.cross.whale.res.ReceiptsInfoPageResVO;
import com.cross.whale.res.ReceiptsInfoResVO;
import com.cross.whale.service.ReceiptsInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/receipts/info")
public class ReceiptsInfoController {

    @Autowired
    private ReceiptsInfoService receiptsInfoService;

    /**
     * 添加请购单
     * @param createReceiptsInfoReqVO
     * @return
     */
    @RequestMapping(value = "/createReceiptsInfo",method = RequestMethod.POST)
    public CommonResult<Void> createReceiptsInfo(@RequestBody CreateReceiptsInfoReqVO createReceiptsInfoReqVO){
        return receiptsInfoService.createReceiptsInfo(createReceiptsInfoReqVO);
    }


    /**
     * 获取请购单详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getReceiptsDetailsInfo",method = RequestMethod.GET)
    public CommonResult<ReceiptsInfoResVO> getReceiptsDetailsInfo(@RequestParam Long id){
        return receiptsInfoService.getReceiptsDetailsInfo(id);
    }


    /**
     * 删除请购单
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteReceiptsInfo",method = RequestMethod.DELETE)
    public CommonResult<Void> deleteReceiptsInfo(@RequestParam Long id){
        return receiptsInfoService.deleteReceiptsInfo(id);
    }

    /**
     * 更新请购单状态
     * @param updateReceiptsStatusReqVO
     * @return
     */
    @RequestMapping(value = "/updateReceiptsStatus",method = RequestMethod.PUT)
    public CommonResult<Void> updateReceiptsStatus(@RequestBody UpdateReceiptsStatusReqVO updateReceiptsStatusReqVO){
        return receiptsInfoService.updateReceiptsStatus(updateReceiptsStatusReqVO);
    }


    /**
     *删除单据
     * @param deleteReceiptsReqVO
     * @return
     */
    @RequestMapping(value = "/deleteReceipts",method = RequestMethod.DELETE)
    public CommonResult<Void> deleteReceipts(@RequestBody DeleteReceiptsReqVO deleteReceiptsReqVO){
        return receiptsInfoService.deleteReceipts(deleteReceiptsReqVO);
    }


    /**
     * 请购单列表
     * @param queryReceiptsReqVO
     * @return
     */
    @RequestMapping(value = "/queryReceiptsPage",method = RequestMethod.POST)
    public CommonResult<PageUtil<ReceiptsInfoPageResVO>> queryReceiptsPage(@RequestBody QueryReceiptsReqVO queryReceiptsReqVO){
        return receiptsInfoService.queryReceiptsPage(queryReceiptsReqVO);
    }

    /**
     * 更新单据信息
     * @param updateReceiptsInfoReqVO
     * @return
     */
    @RequestMapping(value = "/updateReceiptsInfo",method = RequestMethod.PUT)
    public CommonResult<Void> updateReceiptsInfo(@RequestBody UpdateReceiptsInfoReqVO updateReceiptsInfoReqVO){
        return receiptsInfoService.updateReceiptsInfo(updateReceiptsInfoReqVO);
    }
}
