package com.cross.whale.res;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SupplierInfoDetailsResVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;
    /**
     * 供应商状态 0启用1禁用
     */
    private Integer supplierStatus;
    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 供应商邮箱
     */
    private String supplierEmail;

    /**
     * 初期应付
     */
    private BigDecimal initialOping;

    /**
     * 纳税人识别号码
     */
    private String taxpayerIdentificationNumber;

    /**
     * 开户行
     */
    private String openBank;

    /**
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String remake;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系电话
     */
    private String contactNumber;

    /**
     * 传真
     */
    private String supplierFax;

    /**
     * 末期应付
     */
    private BigDecimal lastOping;

    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 账号
     */
    private String account;

    /**
     * 排序值
     */
    private Integer sortValue;
}
