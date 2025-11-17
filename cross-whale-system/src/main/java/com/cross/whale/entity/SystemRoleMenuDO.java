package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cross.whale.mybatis.base.BaseDO;
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
