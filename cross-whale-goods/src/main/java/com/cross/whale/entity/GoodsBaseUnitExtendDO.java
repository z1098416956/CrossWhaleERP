package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cross.whale.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serial;
import java.math.BigDecimal;

/**
 * 基本单位副单位
 */
@Data
@TableName(value = "goods_base_unit_extend")
public class GoodsBaseUnitExtendDO extends BaseDO<GoodsBaseUnitExtendDO> {
    @Serial
    private static final long serialVersionUID = -6155520593458223103L;

    /**
     *单位名称如：瓶、个、千克、米、箱、提
     */
    private String unitExtendName;
    /**
     *基本单位id
     */
    private Long unitId;

    /**
     *转换比例
     */
    private BigDecimal conversionRatio;
}
