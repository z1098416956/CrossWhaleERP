package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: TheSunshine
 * @create: 2024-11-13 10:17
 **/
@Data
public class UpdateRoleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String roleName;

    private String roleCode;
}
