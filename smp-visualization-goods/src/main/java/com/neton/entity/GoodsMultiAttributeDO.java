package com.neton.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neton.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serial;

/**
 * 基本单位副单位
 */
@Data
@TableName(value = "goods_multi_attribute")
public class GoodsMultiAttributeDO extends BaseDO<GoodsMultiAttributeDO> {
    @Serial
    private static final long serialVersionUID = -6155520593458223103L;

    /**
     *多属性名称
     */
    private String attributeName;
    /**
     *多个属性，用|分开
     */
    private String attributeValue;

}
