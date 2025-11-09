package com.neton.req;

import com.neton.common.PageParam;
import lombok.Data;

@Data
public class QueryGoodsInfoReqVO extends PageParam {
    /**
     * 关键词商品条码、名称、助记词、名称
     */
    private String query;

    /**
     * 规格
     */
    private String specification;


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
     * 商品状态 0上架 1下架
     */
    private Integer goodsStatus;

    /**
     * 类别id
     */
    private Long categoryType;


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
     * 是否启用0是1否
     */
    private Integer isEnabled;


}
