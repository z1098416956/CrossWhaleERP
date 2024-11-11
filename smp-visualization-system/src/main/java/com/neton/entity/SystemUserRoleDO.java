package com.neton.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neton.mybatis.base.BaseDO;
import lombok.Data;

@Data
@TableName("system_user_role")
public class SystemUserRoleDO extends BaseDO<SystemUserRoleDO> {
    /**
     *  用户id
     */
    private Long userId;
    /**
     * 角色ID
     */
    private String roleId;
}
