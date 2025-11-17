package com.cross.whale.req;

import com.cross.whale.common.PageParam;
import lombok.Data;

import java.io.Serializable;

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
