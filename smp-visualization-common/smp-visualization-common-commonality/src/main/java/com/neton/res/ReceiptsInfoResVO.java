package com.neton.res;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ReceiptsInfoResVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 单据id
     */
    private Long id;

    /**
     * 单据编号
     */
    private String receiptsNumber;

    /**
     * 备注
     */
    private String remake;

    /**
     * 单据商品详情
     */
    private List<ReceiptsInfoDetailsResVO> receiptsInfoDetails;
}
