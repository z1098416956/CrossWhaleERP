package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CreateReceiptsInfoReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单据编号
     */
    private String receiptsNumber;

    /**
     * 备注
     */
    private String remake;

    /**
     * 据状态 0 未审核 1已审核 2采购强制结单 3采购完成 4部分采购完成
     */
    private Integer receiptsStatus;

    /**
     * 商品详情
     */
    private List<CreateReceiptsInfoDetailsReqVO> details;
}
