package com.cross.whale.res;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsInventoryDetailsResVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 仓库库存id
     */
    private Long id;

    /**
     * 仓库id
     */
    private Long storageId;
    /**
     * 当前库存
     */
    private Long currentStock;
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
