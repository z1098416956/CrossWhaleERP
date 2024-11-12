package com.neton.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: TheSunshine
 * @create: 2024-11-12 15:38
 **/
@Data
public class QuerySystemMenuVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *ID
     */
    private Long id;
    /**
     *菜单类型 M菜单 B按钮
     */
    private String menuType;
    /**
     *菜单名称
     */
    private String menuName;
}
