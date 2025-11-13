package com.neton.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DeleteReceiptsReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ids
     */
    private List<Long> ids;
}
