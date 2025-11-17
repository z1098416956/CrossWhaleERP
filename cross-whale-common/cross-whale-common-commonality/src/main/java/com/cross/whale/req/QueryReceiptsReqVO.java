package com.cross.whale.req;

import com.cross.whale.common.PageParam;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class QueryReceiptsReqVO extends PageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单据开始时间
     */
    private LocalDateTime startTime;
    /**
     * 单据结束时间
     */
    private LocalDateTime endTime;

    /**
     * 商品信息
     */
    private String goodsInfo;

    /**
     * 单据编号
     */
    private String receiptsNumber;
}
