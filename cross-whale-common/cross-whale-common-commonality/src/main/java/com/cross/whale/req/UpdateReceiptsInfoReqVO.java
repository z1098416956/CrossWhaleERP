package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UpdateReceiptsInfoReqVO implements Serializable {

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
     * 据状态 0 未审核 1已审核 2采购强制结单 3采购完成 4部分采购完成
     */
    private Integer receiptsStatus;
    /**
     *申请人ID
     */
    private Long applicantId;

    /**
     *期望到货时间
     */
    private LocalDateTime expectedDate;

    /**
     * 收货仓库ID(非必填)
     */
    private Long warehouseId;
    /**
     * 商品详情
     */
    private List<UpdateReceiptsInfoDetailsReqVO> details;
}
