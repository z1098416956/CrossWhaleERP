package com.cross.whale.req;

import lombok.Data;

@Data
public class CreateAccAccountVO {
    /**
     * 用户名
     */
    private String userName;

    private String name;

    /**
     * 账号
     */
    private String accountNo;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 部门id
     */
    private Long deptId;
}
