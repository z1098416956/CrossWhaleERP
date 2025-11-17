package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: TheSunshine
 * @create: 2024-11-12 14:52
 **/
@Data
public class UpdateSystemMenuVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 父id
     */
    private Long pId;
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
