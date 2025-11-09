package com.neton.res;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsExtendDetailsResVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 扩展信息id
     */
    private Long id;

    /**
     * 扩展信息
     */
    private String goodsValue;
}
