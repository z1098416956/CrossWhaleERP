package com.cross.whale.utils;

public record PurchaseSalesRocketMQConstants() {

    /**
     * 请购单topic
     */
    public static final String RECEIPTS_STATUS_TOPIC = "receipts_status_topic";
    /**
     * 同步请购单商品采购状态
     */
    public static final String RECEIPTS_STATUS_TAG = "receipts_goods_status_syn";

    /**
     * 同步请购单商品采购状态分组
     */
    public static final String RECEIPTS_STATUS_GROUP = "receipts_goods_status_GROUP";
}
