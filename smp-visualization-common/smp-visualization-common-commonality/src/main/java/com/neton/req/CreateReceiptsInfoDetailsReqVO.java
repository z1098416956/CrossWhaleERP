package com.neton.req;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CreateReceiptsInfoDetailsReqVO  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 规格
     */
    private String specification;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 型号
     */
    private String model;

    /**
     * 颜色
     */
    private String colour;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 制造商
     */
    private String manufacturer;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 商品多属性（sku）
     */
    private String goodsSku;

    /**
     * 其他信息json字符串存储
     */
    private String othersInfo;

    /**
     * 采购价
     */
    private BigDecimal purchasePrice;

    /**
     * 备注
     */
    private String remake;

    /**
     * 附件
     */
    private String receiptsAccessory;
}
