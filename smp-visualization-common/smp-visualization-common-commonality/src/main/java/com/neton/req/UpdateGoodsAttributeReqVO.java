package com.neton.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateGoodsAttributeReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 属性单位id
     */
    private Long goodsMultiAttributeId;

    /**
     * 选择的属性
     */
    private String attributeName;
}
