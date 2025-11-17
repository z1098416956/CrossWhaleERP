package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cross.whale.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serial;

/**
 * 商品选择的属性实体类
 * @author TheSunshine
 * @date 2025-11-8
 */
@Data
@TableName(value = "goods_attribute")
public class GoodsAttributeDO extends BaseDO<GoodsAttributeDO> {

    @Serial
    private static final long serialVersionUID = -6155520593458223103L;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 属性单位id
     */
    private Long goodsMultiAttributeId;

    /**
     * 选择的属性
     */
    private String attributeName;
}
