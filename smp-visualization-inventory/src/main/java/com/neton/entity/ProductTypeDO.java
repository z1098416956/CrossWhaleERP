package com.neton.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neton.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serial;

@Data
@TableName("product_type")
public class ProductTypeDO extends BaseDO<ProductTypeDO> {
    @Serial
    private static final long serialVersionUID = -6155520593458223103L;

    /**
     *分类名称
     */
    private String typeName;
    /**
     *父级id
     */
    private Long pId;
}
