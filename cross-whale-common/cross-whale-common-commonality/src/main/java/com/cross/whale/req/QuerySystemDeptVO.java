package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: TheSunshine
 * @create: 2024-11-12 11:40
 **/
@Data
public class QuerySystemDeptVO  implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 父id
     */
    private Long pid;

    private Long id;

    private Long page;

    private Long size;
}
