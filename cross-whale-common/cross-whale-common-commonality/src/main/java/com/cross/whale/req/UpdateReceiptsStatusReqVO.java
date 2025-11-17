package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateReceiptsStatusReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private List<Long> ids;
    /**
     * 据状态 0 未审核 1已审核 3采购完成 4部分采购完成
     */
    private Integer receiptsStatus;
}
