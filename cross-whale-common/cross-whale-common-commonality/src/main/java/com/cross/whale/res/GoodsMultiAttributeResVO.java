package com.cross.whale.res;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsMultiAttributeResVO implements Serializable {

    private static final long serialVersionUID = -6155520593458223103L;
    private Long id;
    /**
     *多属性名称
     */
    private String attributeName;
    /**
     *多个属性，用|分开
     */
    private String attributeValue;
}
