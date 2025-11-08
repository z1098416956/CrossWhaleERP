package com.neton.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neton.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serial;

@Data
@TableName(value = "goods_extend")
public class GoodsExtendDO  extends BaseDO<GoodsExtendDO> {

    @Serial
    private static final long serialVersionUID = -6155520593458223103L;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 扩展信息
     */
    private String goodsValue;
}
