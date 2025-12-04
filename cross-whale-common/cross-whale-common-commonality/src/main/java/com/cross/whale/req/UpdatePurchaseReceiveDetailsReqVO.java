package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UpdatePurchaseReceiveDetailsReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 采购订单明细ID
     */
    private Long purchaseDetailId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品属性ID
     */
    private Long goodsAttributeId;

    /**
     * 商品SKU
     */
    private String goodsSku;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 订单订购数量
     */
    private BigDecimal expectedQty;

    /**
     * 本次实收数量
     */
    private BigDecimal receivedQty;

    /**
     * 累计收货数量
     */
    private BigDecimal accumulatedReceivedQty;

    /**
     * 质检合格数量
     */
    private BigDecimal qcPassQty;

    /**
     * 质检不合格数量
     */
    private BigDecimal qcRejectQty;

    /**
     * 不合格原因
     */
    private String qcRejectReason;

    /**
     * 已上架数量
     */
    private BigDecimal putawayQty;

    /**
     * 上架库位ID
     */
    private Long locationId;

    /**
     * 库位编码
     */
    private String locationCode;

    /**
     * 行状态(0-5)
     * 0 草稿 刚创建，未提交审核 可编辑、删除、提交
     * 1 待收货 已审核通过，等待收货 可开始收货、取消
     * 2 收货中 正在接收实物 可录入收货数量、完成收货、暂停
     * 3 待质检 收货完成，等待质检 可开始质检、退回收货
     * 4 质检中 正在质量检验 可录入质检结果
     * 5 待上架 质检合格，等待上架 可开始上架、退回质检
     */
    private Integer lineStatus;
}
