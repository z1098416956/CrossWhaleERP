package com.neton.entity;

import com.neton.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PurchaseInfoDetailsDO extends BaseDO<PurchaseInfoDetailsDO> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品条码
     */
    private String goodsBarcode;

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
     * 总金额
     */
    private BigDecimal totalPrice;

    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 税额
     */
    private BigDecimal taxAmount;

    /**
     * 价税合计
     */
    private BigDecimal taxPrice;

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
