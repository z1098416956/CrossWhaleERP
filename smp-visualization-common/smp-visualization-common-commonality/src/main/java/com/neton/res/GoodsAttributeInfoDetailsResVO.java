package com.neton.res;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class GoodsAttributeInfoDetailsResVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 采购属性id
     */
    private Long id;
    /**
     * 条码
     */
    private String barCode;

    /**
     * 单位名称
     */
    private String unitName;

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
