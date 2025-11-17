package com.cross.whale.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cross.whale.mybatis.base.BaseDO;
import lombok.Data;

@Data
@TableName("system_menu")
public class SystemMenuDO extends BaseDO<SystemMenuDO> {
    /**
     *  父ID
     */
    private Long pId;
    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单类型 M菜单 B按钮
     */
    private String menuType;
    /**
     * 菜单图标
     */
    private String menuIcon;
}
