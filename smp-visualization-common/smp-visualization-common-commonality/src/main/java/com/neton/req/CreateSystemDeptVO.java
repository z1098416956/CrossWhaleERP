package com.neton.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: TheSunshine
 * @create: 2024-11-11 18:19
 **/
@Data
public class CreateSystemDeptVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 父id
     */
    private Long pid;
}
