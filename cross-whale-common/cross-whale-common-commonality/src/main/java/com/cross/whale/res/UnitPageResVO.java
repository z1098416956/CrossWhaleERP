package com.cross.whale.res;

import lombok.Data;

import java.io.Serializable;

@Data
public class UnitPageResVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;
    /**
     *单位名称
     */
    private String baseUnitName;
    /**
     *基本单位
     */
    private String unitName;
    /**
     * 副单位
     */
    private String deputyUnitName;
    /**
     * 副单位2
     */
    private String deputyUnitName2;
    /**
     * 副单位3
     */
    private String deputyUnitName3;
    /**
     *单位类型（如：基本单位、包装单位、重量单位等，可选）
     */
    private Integer unitType;

    /**
     * 是否启用0是1否
     */
    private Integer isEnabled;
}
