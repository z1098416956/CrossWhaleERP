package com.neton.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateBrandTypeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long pid;

    private String remark;

    private String brandName;
}
