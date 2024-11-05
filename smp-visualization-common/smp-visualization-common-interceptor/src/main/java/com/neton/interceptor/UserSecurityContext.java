package com.neton.interceptor;

import lombok.Data;

import java.util.List;

@Data
public class UserSecurityContext {

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 真实姓名
     */
    private String account;

    /**
     * 用户名
     */
    private String username;

    /**
     * 角色集合
     */
    private List<String> roles;
}
