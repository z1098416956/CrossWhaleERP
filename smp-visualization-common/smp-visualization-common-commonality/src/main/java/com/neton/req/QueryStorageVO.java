package com.neton.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QueryStorageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long page;

    private Long size;

    private String storageName;

    private String cityId;

    private List<String> cityIds;

}
