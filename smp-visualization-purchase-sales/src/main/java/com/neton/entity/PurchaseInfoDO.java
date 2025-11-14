package com.neton.entity;

import com.neton.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PurchaseInfoDO extends BaseDO<PurchaseInfoDO> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 请购单id
     */
    private Long receiptsId;

    /**
     * 供应商id
     */
    private Long supplierId;

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
     * 创建人id
     */
    private Long createBy;

    /**
     * 创建人
     */
    private String createByName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人id
     */
    private Long updateBy;

    /**
     * 修改人
     */
    private String updateByName;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否已删除 1-已删除 0-未删除
     */
    private Integer isDeleted;

    /**
     * 版本号
     */
    private Long version;
}
