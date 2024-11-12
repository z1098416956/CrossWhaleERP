package com.neton.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neton.mybatis.base.BaseDO;
import lombok.Data;

@Data
@TableName("system_role_menu")
public class SystemRoleMenuDO extends BaseDO<SystemRoleMenuDO> {
    /**
     *  角色id
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;
}
