package com.neton.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateUnitStatusReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 基本单位id
     */
    private List<Long> ids;

    /**
     * 操作类型 1批量删除 2 批量禁用 3批量启用
     */
    private Integer type;
}
