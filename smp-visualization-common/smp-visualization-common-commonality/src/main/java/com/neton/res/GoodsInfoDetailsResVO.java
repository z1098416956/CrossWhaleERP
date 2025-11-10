package com.neton.res;

import com.neton.req.UpdateGoodsAttributeInfoReqVO;
import com.neton.req.UpdateGoodsAttributeReqVO;
import com.neton.req.UpdateGoodsExtendReqVO;
import com.neton.req.UpdateGoodsInventoryReqVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GoodsInfoDetailsResVO implements Serializable {

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
     * 自定义单位
     */
    private String customUnit;

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
     * 单位采购、零售、销售、最低价
     */
    private List<GoodsAttributeInfoDetailsResVO> attributes;

    /**
     * 扩展信息
     */
    private List<GoodsExtendDetailsResVO> extendInfo;

    /**
     * 库存数量
     */
    private List<GoodsInventoryDetailsResVO> inventoryInfo;

    /**
     * 选择的属性
     */
    private List<GoodsAttributeDetailsResVO> attributeRes;
}
