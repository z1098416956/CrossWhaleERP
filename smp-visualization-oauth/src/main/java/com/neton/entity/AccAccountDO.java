package com.neton.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neton.mybatis.base.BaseDO;
import com.neton.deserializer.AccountDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Set;

/**
 * @author TheSunshine
 * @date 2024-10-30 15:52:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName(value = "acc_account")
@JsonDeserialize(using = AccountDeserializer.class)
public class AccAccountDO extends BaseDO<AccAccountDO> implements UserDetails {

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
     * 权限列表
     */
    @TableField(value = "authorities", exist = false)
    Set<GrantedAuthority> authorities;

    @Override
    public String getPassword() {
        return accountPassword;
    }

    @Override
    public String getUsername() {
        return accountName;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
