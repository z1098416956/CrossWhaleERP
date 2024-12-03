package com.neton.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author TheSunshine
 * @date 2024-11-08 16:14:41
 */
@Data
public class UpdateAccAccountVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accountNo;

    private String avatar;

    private String name;

    private String introduction;

    private Long id;

    private Long deptId;
}
