package com.neton.req;

import com.neton.common.PageParam;
import lombok.Data;

import java.io.Serializable;

@Data
public class QueryUnitReqVO  extends PageParam implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 单位名称
     */
    private String unitName;
}
