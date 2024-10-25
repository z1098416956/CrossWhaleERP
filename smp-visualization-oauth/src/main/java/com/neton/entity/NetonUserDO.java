package com.neton.entity;

import lombok.Data;

/**
 * @author TheSunshine
 * @date 2024-10-25 13:51:27
 */
@Data
public class NetonUserDO {

    private String userName;

    private String password;

    private Integer isActive;

    private String role;
}
