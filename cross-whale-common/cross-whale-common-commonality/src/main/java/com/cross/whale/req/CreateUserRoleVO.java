package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CreateUserRoleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private List<Long> roleList;


}
