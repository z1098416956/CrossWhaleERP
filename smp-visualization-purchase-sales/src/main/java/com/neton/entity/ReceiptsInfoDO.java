package com.neton.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neton.mybatis.base.BaseDO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 请购单实体类
 * @author TheSunshine
 * @date 2025-11-13 11:24:15
 */
@Data
@TableName(value = "r_receipts_info")
public class ReceiptsInfoDO extends BaseDO<ReceiptsInfoDO> {

    /**
     * 单据编号
     */
    private String receiptsNumber;

    /**
     * 商品信息名称+规格+型号+颜色多个用|分割开
     */
    private String goodsInfo;

    /**
     * 商品数量
     */
    private Integer goodsQuantity;

    /**
     * 单据时间
     */
    private LocalDateTime receiptsTime;

    /**
     * 单据状态 0 未审核 1已审核
     */
    private Integer receiptsStatus;

    /**
     * 备注
     */
    private String remake;
}
