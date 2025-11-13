package com.neton.feign.purchase;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.FeignConfig;
import com.neton.req.*;
import com.neton.res.ReceiptsInfoPageResVO;
import com.neton.res.ReceiptsInfoResVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "smp-purchase-salse-service" ,configuration = FeignConfig.class,contextId = "purchase-service")
public interface PurchaseClient {

    /**
     * 添加请购单
     *
     * @param createReceiptsInfoReqVO
     * @return
     */
    @RequestMapping(value = "/v1/receipts/info/createReceiptsInfo", method = RequestMethod.POST)
    public CommonResult<Void> createReceiptsInfo(@RequestBody CreateReceiptsInfoReqVO createReceiptsInfoReqVO);


    /**
     * 获取请购单详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/v1/receipts/info/getReceiptsDetailsInfo", method = RequestMethod.GET)
    public CommonResult<ReceiptsInfoResVO> getReceiptsDetailsInfo(@RequestParam Long id);


    /**
     * 删除请购单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/v1/receipts/info/deleteReceiptsInfo", method = RequestMethod.DELETE)
    public CommonResult<Void> deleteReceiptsInfo(@RequestParam Long id);

    /**
     * 更新请购单状态
     *
     * @param updateReceiptsStatusReqVO
     * @return
     */
    @RequestMapping(value = "/v1/receipts/info/updateReceiptsStatus", method = RequestMethod.PUT)
    public CommonResult<Void> updateReceiptsStatus(@RequestBody UpdateReceiptsStatusReqVO updateReceiptsStatusReqVO);


    /**
     * 删除单据
     *
     * @param deleteReceiptsReqVO
     * @return
     */
    @RequestMapping(value = "/v1/receipts/info/deleteReceipts", method = RequestMethod.DELETE)
    public CommonResult<Void> deleteReceipts(@RequestBody DeleteReceiptsReqVO deleteReceiptsReqVO);

    /**
     * 请购单列表
     *
     * @param queryReceiptsReqVO
     * @return
     */
    @RequestMapping(value = "/v1/receipts/info/queryReceiptsPage", method = RequestMethod.POST)
    public CommonResult<PageUtil<ReceiptsInfoPageResVO>> queryReceiptsPage(@RequestBody QueryReceiptsReqVO queryReceiptsReqVO);

    /**
     * 更新单据信息
     *
     * @param updateReceiptsInfoReqVO
     * @return
     */
    @RequestMapping(value = "/v1/receipts/info/updateReceiptsInfo", method = RequestMethod.PUT)
    public CommonResult<Void> updateReceiptsInfo(@RequestBody UpdateReceiptsInfoReqVO updateReceiptsInfoReqVO);


}
