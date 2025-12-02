package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreatePurchaseInfoReqVO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 请购单id
     */
    private Long receiptsId;
    /**
     * 请购单编号
     */
    private String receiptsNumber;
    /**
     * 供应商id
     */
    private Long supplierId;
    /**
     * 采购单状态 0 未审核 1已审核 2强制结单 3采购完成 4部分采购完成
     */
    private Integer purchaseStatus = 0;

    /**
     * 采购单时间
     */
    private LocalDateTime receiptsTime;

    /**
     * 采购单号
     */
    private String purchaseNumber;

    /**
     * 优惠率
     */
    private BigDecimal discountRate;

    /**
     * 优惠金额
     */
    private BigDecimal discountsPrice;

    /**
     * 付款优惠
     */
    private BigDecimal paymentDiscount;

    /**
     * 付款账户id
     */
    private Long paymentAccountId;

    /**
     * 付款账户
     */
    private String paymentAccountName;

    /**
     * 附件地址
     */
    private String fileUrl;

    /**
     * 备注
     */
    private String remake;

    /**
     * 商品信息
     */
    private List<CreatePurchaseInfoDetailsReqVO> details;
}
