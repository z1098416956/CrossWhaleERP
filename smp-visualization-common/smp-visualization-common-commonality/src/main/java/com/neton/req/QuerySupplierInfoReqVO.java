package com.neton.req;

import com.neton.common.PageParam;
import lombok.Data;

@Data
public class QuerySupplierInfoReqVO extends PageParam {

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 联系人
     */
    private String contactPerson;
}
