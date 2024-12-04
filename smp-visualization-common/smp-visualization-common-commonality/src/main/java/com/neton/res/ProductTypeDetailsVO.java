package com.neton.res;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductTypeDetailsVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *分类名称
     */
    private String typeName;
    /**
     *父级id
     */
    private Long pId;

    private Long id;
}
