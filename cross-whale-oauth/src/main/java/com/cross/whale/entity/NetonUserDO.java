package com.cross.whale.entity;

import lombok.Data;

/**
 * @author TheSunshine
 * @date 2024-10-25 13:51:27
 */
@Data
public class NetonUserDO {

    public NetonUserDO(String userName, String password, boolean enabled, String role) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }

    public NetonUserDO(){}

    private String userName;

    private String password;

    private boolean enabled;

    private String role;
}
