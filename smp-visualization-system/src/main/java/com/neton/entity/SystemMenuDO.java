package com.neton.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.neton.mybatis.base.BaseDO;
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
}
