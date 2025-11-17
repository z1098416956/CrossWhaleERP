package com.cross.whale.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UpdateSettlementAccountStatusReqVO  implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id集合
     */
    private List<Long> ids;

    /**
     * id
     */
    private Long id;
    /**
     * 是否默认0是1否
     */
    private Integer isDefault;

    /**
     * 账户状态0启用1禁用
     */
    private Integer accountStatus;
}
