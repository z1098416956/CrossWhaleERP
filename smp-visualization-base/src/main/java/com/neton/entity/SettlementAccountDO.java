package com.neton.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neton.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@TableName("s_settlement_account")
@Data
public class SettlementAccountDO extends BaseDO<SettlementAccountDO> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 是否默认0是1否
     */
    private Integer isDefault;
    /**
     * 账户状态0启用1禁用
     */
    private Integer accountStatus;
    /**
     * 编号
     */
    private String accountNumber;

    /**
     * 初期金额
     */
    private BigDecimal startAmount;

    /**
     * 排序
     */
    private Integer sortValue;

    /**
     * 备注
     */
    private String remake;

}
