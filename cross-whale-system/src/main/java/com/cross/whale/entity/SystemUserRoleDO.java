package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cross.whale.mybatis.base.BaseDO;
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
    private Long roleId;
}
