package com.neton.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neton.mybatis.base.BaseDO;
import lombok.Data;

@Data
@TableName("system_role")
public class SystemRoleDO extends BaseDO<SystemRoleDO> {
    /**
     *  父ID
     */
    private Long pId;
    /**
     * 角色名称
     */
    private String roleName;
}
