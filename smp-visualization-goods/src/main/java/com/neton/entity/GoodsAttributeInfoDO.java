package com.neton.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neton.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serial;
import java.math.BigDecimal;

/**
 * 商品属性信息实体类
 * @author TheSunshine
 * @date 2024-11-12
 */
@Data
@TableName(value = "goods_attribute_info")
public class GoodsAttributeInfoDO extends BaseDO<GoodsAttributeInfoDO> {
    
    @Serial
    private static final long serialVersionUID = -6155520593458223103L;

    /**
     * 商品id
     */
    private Long goodsId;

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
