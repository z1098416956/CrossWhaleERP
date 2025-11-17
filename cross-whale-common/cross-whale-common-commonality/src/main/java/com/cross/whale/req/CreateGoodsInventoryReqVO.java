package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateGoodsInventoryReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库id
     */
    private Long storageId;

    /**
     * 初期库存数量
     */
    private Long firstCount;

    /**
     * 最低库存数量
     */
    private Long lowCount;

    /**
     * 最高库存数量
     */
    private Long highCount;
}
