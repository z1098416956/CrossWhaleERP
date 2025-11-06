package com.neton.res;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UnitExtendResVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *单位名称如：瓶、个、千克、米、箱、提
     */
    private String unitName;

    private Long id;
    /**
     *
     */
    private Long unitId;

    /**
     *转换比例
     */
    private BigDecimal conversionRatio;
}
