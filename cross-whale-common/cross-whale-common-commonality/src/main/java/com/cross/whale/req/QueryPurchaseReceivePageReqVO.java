package com.cross.whale.req;

import com.cross.whale.common.PageParam;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QueryPurchaseReceivePageReqVO extends PageParam {

    /**
     * 入库单编号
     */
    private String receiveNumber;

    /**
     * 商品信息
     */
    private String goodsInfo;
    /**
     * 单据开始时间
     */
    private LocalDateTime startTime;
    /**
     * 单据结束时间
     */
    private LocalDateTime endTime;
}
