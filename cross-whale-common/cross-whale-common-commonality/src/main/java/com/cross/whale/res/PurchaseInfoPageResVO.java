package com.cross.whale.res;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PurchaseInfoPageResVO  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单据详情id
     */
    private Long id;

    /**
     * 商品数量
     */
    private Integer goodsQuantity;
    /**
     * 请购单编号
     */
    private String receiptsNumber;
    /**
     * 商品信息
     */
    private String goodsInfo;
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
     * 定金
     */
    private BigDecimal depositAmount;
    /**
     * 金额合计
     */
    private BigDecimal totalAmount;
    /**
     * 含税合计
     */
    private BigDecimal taxAmount;
    /**
     * 请购单编号
     */
    private String receiptsNumber;

    /**
     * 备注
     */
    private String remake;
    /**
     * 采购单状态 0 未审核 1已审核 2强制结单 3采购完成 4部分采购完成
     */
    private Integer purchaseStatus;

    /**
     * 附件
     */
    private String receiptsAccessory;

    /**
     * 要求到货日期
     */
    private LocalDateTime deliveryDate;

}
