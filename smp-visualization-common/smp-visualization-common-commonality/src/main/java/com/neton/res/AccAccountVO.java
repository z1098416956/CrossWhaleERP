package com.neton.res;

import lombok.Data;

import java.io.Serializable;

/**
 * @author TheSunshine
 * @date 2024-10-31 14:55:33
 */
@Data
public class AccAccountVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String accountName;

    private Boolean enabled;

    private String accountNo;


}
