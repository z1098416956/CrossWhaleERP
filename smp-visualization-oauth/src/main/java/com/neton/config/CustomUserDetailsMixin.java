package com.neton.config;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neton.entity.NetonUserDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author TheSunshine
 * @date 2024-10-29 15:44:37
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY)
@JsonDeserialize(using = CustomUserDeserializer.class)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CustomUserDetailsMixin {

    @JsonProperty("authorities")
    @JsonDeserialize(using = CustomUserDeserializer.class)
    abstract Set<SimpleGrantedAuthority> getAuthorities();

    @JsonCreator
    public CustomUserDetailsMixin(@JsonProperty("netonUserDO") NetonUserDO netonUserDO) {}



    @JsonProperty("password")
    abstract String getPassword();

    @JsonProperty("username")
    abstract String getUsername();

    @JsonProperty("accountNonExpired")
    abstract boolean isAccountNonExpired();

    @JsonProperty("accountNonLocked")
    abstract boolean isAccountNonLocked();

    @JsonProperty("credentialsNonExpired")
    abstract boolean isCredentialsNonExpired();

    @JsonProperty("enabled")
    abstract boolean isEnabled();
}
