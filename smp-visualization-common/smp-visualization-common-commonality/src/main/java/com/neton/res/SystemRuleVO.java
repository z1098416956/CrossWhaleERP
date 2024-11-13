package com.neton.res;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: TheSunshine
 * @create: 2024-11-13 10:21
 **/
@Data
public class SystemRuleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    private String roleCode;
}
