package com.neton.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author TheSunshine
 * @date 2024-10-31 15:00:02
 */
@Data
public class QueryAccAccountVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String name;

    private String accountNo;
    private String password;

    private Long page;

    private Long size;

    private Long deptId;

    private List<Long> deptIds;

    private List<Long> ids;
}
