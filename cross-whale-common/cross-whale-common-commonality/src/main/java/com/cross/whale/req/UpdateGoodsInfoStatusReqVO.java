package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateGoodsInfoStatusReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private List<Long> goodsIds;

    /**
     *是否启用0是1否
     */
    private Integer isEnabled;
}
