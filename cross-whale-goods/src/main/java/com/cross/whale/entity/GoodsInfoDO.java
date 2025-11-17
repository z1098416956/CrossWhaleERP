package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cross.whale.mybatis.base.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 商品基本信息实体类
 * @author TheSunshine
 * @date 2025-11-8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "goods_info")
public class GoodsInfoDO extends BaseDO<GoodsInfoDO> {

    @Serial
    private static final long serialVersionUID = -6155520593458223103L;

    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 条码
     */
    private String goodsBarcode;
    /**
     * 规格
     */
    private String specification;

    /**
     * 型号
     */
    private String model;

    /**
     * 是否多单位 0是 1否
     */
    private Integer isUnit;
    /**
     * 基础重量
     */
    private Integer basicWeight;
    /**
     * 自定义单位
     */
    private String customUnit;
    /**
     * 保质期
     */
    private Integer shelfLife;
    /**
     * 仓位货架
     */
    private String positionShelves;
    /**
     * 单位id
     */
    private Long unitId;

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

    /**
     * 多属性 多个id用逗号分隔
     */
    private String multiattribute;
}
