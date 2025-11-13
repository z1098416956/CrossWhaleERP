package com.neton.res;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ReceiptsInfoPageResVO  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单据详情id
     */
    private Long id;
    /**
     * 商品信息
     */
    private String goodsInfo;
    /**
     * 单据时间
     */
    private String receiptsTimeStr;
    /**
     * 单据时间
     */
    private LocalDateTime receiptsTime;
    /**
     * 据状态 0 未审核 1已审核 3采购完成 4部分采购完成
     */
    private Integer receiptsStatus;

    /**
     * 操作员
     */
    private String createBy;

    /**
     * 备注
     */
    private String remake;

    /**
     * 商品数量
     */
    private Integer goodsQuantity;
}
