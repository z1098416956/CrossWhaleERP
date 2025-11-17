package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cross.whale.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serial;

/**
 * 商品库存实体类
 * @author TheSunshine
 * @date 2024-11-12
 */
@Data
@TableName(value = "goods_inventory")
public class GoodsInventoryDO extends BaseDO<GoodsInventoryDO> {

    @Serial
    private static final long serialVersionUID = -6155520593458223103L;

    /**
     * 仓库id
     */
    private Long storageId;

    /**
     * 初期库存数量
     */
    private Long firstCount;

    /**
     * 当前库存
     */
    private Long currentStock;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 最低库存数量
     */
    private Long lowCount;

    /**
     * 最高库存数量
     */
    private Long highCount;
}
