package com.neton.req;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CreateGoodsAttributeInfoReqVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 条码
     */
    private String barCode;

    /**
     * 单位名称
     */
    private String unitName;
    /**
     * 商品多属性
     */
    private String goodsSku;

    /**
     * 多属性
     */
    private String multiattribute;

    /**
     * 采购价
     */
    private BigDecimal purchasePrice;

    /**
     * 零售价
     */
    private BigDecimal retailPrice;

    /**
     * 销售价
     */
    private BigDecimal sellingPrice;

    /**
     * 最低售价
     */
    private BigDecimal lowPrice;
}
