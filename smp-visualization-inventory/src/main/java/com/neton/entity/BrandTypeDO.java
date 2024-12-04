package com.neton.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neton.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serial;

@Data
@TableName("brand_type")
public class BrandTypeDO extends BaseDO<BrandTypeDO> {
    @Serial
    private static final long serialVersionUID = -6155520593458223103L;

    /**
     *品牌名称
     */
    private String brandName;
    /**
     *父级id
     */
    private Long pId;
}
