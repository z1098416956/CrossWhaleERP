package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QueryProductTypeVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *分类名称
     */
    private String typeName;
    /**
     *父级id
     */
    private Long pid;

    private Long page;

    private Long size;

    private List<Long> ids;
}
