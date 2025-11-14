package com.neton.req;

import com.neton.common.PageParam;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class QuerySettlementAccountReqVO extends PageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账户名称
     */
    private String accountName;


    /**
     * 编号
     */
    private String accountNumber;


}
