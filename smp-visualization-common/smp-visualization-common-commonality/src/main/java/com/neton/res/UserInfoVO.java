package com.neton.res;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoVO {

    /**
     * 权限
     */
    private List<String> roles;
    /**
     * 描述信息
     */
    private String introduction;
    /**
     * 名称
     */
    private String name;
    /**
     * 头像
     */
    private String avatar;
}
