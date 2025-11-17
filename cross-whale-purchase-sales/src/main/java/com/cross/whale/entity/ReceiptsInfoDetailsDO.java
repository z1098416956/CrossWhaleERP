package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cross.whale.mybatis.base.BaseDO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 请购单详情实体类
 * @author TheSunshine
 * @date 2024-10-30 15:52:15
 */
@Data
@TableName(value = "r_receipts_info_details")
public class ReceiptsInfoDetailsDO extends BaseDO<ReceiptsInfoDetailsDO> {

    /**
     * 请购单id
     */
    private Long receiptsId;

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
     * 商品条码
     */
    private String goodsBarcode;
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
