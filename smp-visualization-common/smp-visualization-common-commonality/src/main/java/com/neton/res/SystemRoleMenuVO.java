package com.neton.res;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: TheSunshine
 * @create: 2024-11-12 17:42
 **/
@Data
public class SystemRoleMenuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private List<Long> menuIds;
}
