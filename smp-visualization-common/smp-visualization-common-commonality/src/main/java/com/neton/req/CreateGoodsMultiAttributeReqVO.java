package com.neton.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateGoodsMultiAttributeReqVO implements Serializable {

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
