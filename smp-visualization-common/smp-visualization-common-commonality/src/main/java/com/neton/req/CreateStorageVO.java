package com.neton.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateStorageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long adminId;

    private Long cityId;

    private String storageName;
}
