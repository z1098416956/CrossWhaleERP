package com.neton.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: TheSunshine
 * @create: 2024-11-13 11:22
 **/
@Data
public class QueryRoleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String roleName;

    private String roleCode;
}
