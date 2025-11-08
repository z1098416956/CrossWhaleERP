package com.neton.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateGoodsExtendReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 扩展信息
     */
    private String goodsValue;
}
