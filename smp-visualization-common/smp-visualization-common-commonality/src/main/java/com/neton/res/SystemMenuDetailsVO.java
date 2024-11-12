package com.neton.res;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: TheSunshine
 * @create: 2024-11-12 15:28
 **/
@Data
public class SystemMenuDetailsVO implements Serializable {

    private static final long serialVersionUID = 1L;

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

    private Long id;
}
