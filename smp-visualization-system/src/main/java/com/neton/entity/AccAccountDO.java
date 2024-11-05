package com.neton.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.neton.mybatis.base.BaseDO;
import lombok.Data;

import java.io.Serial;

/**
 * @author TheSunshine
 * @date 2024-10-31 14:49:52
 */
@Data
@TableName(value = "acc_account")
public class AccAccountDO extends BaseDO<AccAccountDO> {
    @Serial
    private static final long serialVersionUID = -6155520593458223103L;

    /**
     * 账号
     */
    @TableField(value = "account_no")
    private String accountNo;

    /**
     * 账户名称
     */
    @TableField(value = "account_name")
    private String accountName;

    /**
     * 账户密码
     */
    @TableField(value = "account_password")
    private String accountPassword;

    /**
     * 是否启用
     */
    @TableField(value = "enabled")
    private Boolean enabled;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

}
