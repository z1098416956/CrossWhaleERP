package com.neton.dto;

import com.fasterxml.jackson.annotation.*;
import com.neton.entity.NetonUserDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


/**
 * @author TheSunshine
 * @date 2024-10-25 13:52:20
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomUserDetails implements UserDetails{


    private NetonUserDO netonUserDO;



    @JsonCreator
    public CustomUserDetails(NetonUserDO netonUserDO) {
        this.netonUserDO = netonUserDO;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 返回用户的角色或权限
        return Collections.singleton(new SimpleGrantedAuthority(netonUserDO.getRole()));
    }


    @Override
    public String getPassword() {
        return netonUserDO.getPassword();
    }


    @Override
    public String getUsername() {
        return netonUserDO.getUserName();
    }



    @Override
    public boolean isAccountNonExpired() {
        // 账户是否未过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 账户是否未锁定
        return true; // 确保这里返回 true
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 凭据是否未过期
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 账户是否启用
        return true;
    }

}
