package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cross.whale.mybatis.base.BaseDO;
import lombok.Data;

@Data
@TableName("system_role")
public class SystemRoleDO extends BaseDO<SystemRoleDO> {

    /**
     * 角色名称
     */
    private String roleName;
    private String roleCode;
}
