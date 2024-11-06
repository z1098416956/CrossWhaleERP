package com.neton.req;

import lombok.Data;

@Data
public class CreateAccAccountVO {
    /**
     * 用户名
     */
    private String username;
    /**
     * 账号
     */
    private String accountNo;
    /**
     * 头像
     */
    private String avatar;
}
