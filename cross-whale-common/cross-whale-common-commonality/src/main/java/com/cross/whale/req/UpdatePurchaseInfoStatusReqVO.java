package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdatePurchaseInfoStatusReqVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;

    /**
     * 采购单状态 0 未审核 1已审核 2强制结单 3采购完成 4部分采购完成
     */
    private Integer purchaseStatus;
}
