package com.neton.res;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReceiptsInfoResVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 单据id
     */
    private Long id;

    /**
     * 单据编号
     */
    private String receiptsNumber;
    /**
     * 据状态 0 未审核 1已审核 2采购强制结单 3采购完成 4部分采购完成
     */
    private Integer receiptsStatus;

    /**
     * 单据时间
     */
    private LocalDateTime receiptsTime;

    /**
     * 备注
     */
    private String remake;

    /**
     * 单据商品详情
     */
    private List<ReceiptsInfoDetailsResVO> receiptsInfoDetails;
}
