package com.neton.res;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GoodsInfoReceiptsPageResVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 商品id
     */
    private Long id;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 保质期
     */
    private Integer shelfLife;
    /**
     * 仓位货架
     */
    private String positionShelves;
    /**
     * 规格
     */
    private String specification;
    /**
     * 条码
     */
    private String goodsBarcode;
    /**
     * 型号
     */
    private String model;


    /**
     * 自定义单位
     */
    private String customUnit;


    /**
     * 商品主图，多个用逗号分割开
     */
    private String goodsImages;

    /**
     * 颜色
     */
    private String colour;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 助记码
     */
    private String mnemonicCode;


    /**
     * 类别id
     */
    private Long categoryType;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 制造商
     */
    private String manufacturer;

    /**
     * 序列号 0 有 1无 如果选择为有，则在采购入库单需要录入该商品的序列号
     */
    private Integer serialNumber;

    /**
     * 批号 0 有 1无 如果选择为有，则在采购入库单需要录入该商品的批号和有效期
     */
    private Integer lotNumber;


    /**
     * 当前库存
     */
    private Long currentStock;

    /**
     * 品牌名称
     */
    private String brandName;


    /**
     * 商品扩展
     */
    private List<String> goodsExtend;

    /**
     * 单位与多属性和售价多属性（SKU）
     */
    private List<GoodsAttributeInfoDetailsResVO> goodsSKU;
}
