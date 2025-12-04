package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cross.whale.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName(value = "r_purchase_info")
@Data
public class PurchaseInfoDO extends BaseDO<PurchaseInfoDO> implements Serializable {

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
     * 供应商名称
     */
    private String supplierName;
    /**
     * 收货仓库ID
     */
    private Long warehouseId;
    /**
     * 收货仓库名称
     */
    private String warehouseName;
    /**
     * 要求到货日期
     */
    private LocalDateTime deliveryDate;
    /**
     * 商品数量
     */
    private Integer goodsQuantity;

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
     * 采购单状态 0 未审核 1已审核 2强制结单 3采购完成 4部分采购完成
     */
    private Integer purchaseStatus;

}
