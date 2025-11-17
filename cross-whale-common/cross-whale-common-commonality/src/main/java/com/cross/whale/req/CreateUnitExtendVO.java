package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CreateUnitExtendVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *单位名称如：瓶、个、千克、米、箱、提
     */
    private String unitExtendName;

    /**
     *转换比例
     */
    private BigDecimal conversionRatio;
}
