package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: TheSunshine
 * @create: 2024-11-12 17:02
 **/
@Data
public class CreateRoleMenuVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private List<Long> menuIds;
}
