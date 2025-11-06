package com.neton.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CreateUnitVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *单位名称如：瓶、个、千克、米、箱、提
     */
    private String unitName;
    /**
     *单位类型（如：基本单位、包装单位、重量单位等，可选）
     */
    private Integer unitType;

    /**
     * 副单位列表
     */
    private List<CreateUnitExtendVO> extendList;
}
