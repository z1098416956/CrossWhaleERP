package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cross.whale.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serial;

/**
 * 基本单位副单位
 */
@Data
@TableName(value = "goods_base_unit")
public class GoodsBaseUnitDO extends BaseDO<GoodsBaseUnitDO> {
    @Serial
    private static final long serialVersionUID = -6155520593458223103L;

    /**
     *单位名称如：瓶、个、千克、米、箱、提
     */
    private String unitName;
    /**
     *单位类型（如：基本单位、包装单位、重量单位等，可选）
     */
    private Integer unitType;
    /**
     * 是否启用1是0否
     */
    private Integer isEnabled;
}
