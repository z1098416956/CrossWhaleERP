package com.cross.whale.service;

import com.cross.whale.common.CommonResult;
import com.cross.whale.req.CreateUserRoleVO;
import com.cross.whale.res.SystemRuleVO;

import java.util.List;

/**
 * @author: TheSunshine
 * @create: 2024-11-11 18:11
 **/
public interface SystemUserRoleService {
    /**
     * 获取用户角色
     * @return
     */
    public CommonResult<List<SystemRuleVO>> getUserRoleInfo(Long id);


    /**
     * 创建用户权限
     * @param createUserRoleVO
     * @return
     */
    public CommonResult createUserRoleInfo(CreateUserRoleVO createUserRoleVO);

    /**
     * 获取条码
     * @param moduleName
     * @return
     */
    CommonResult<String> getBarcode(String moduleName);

    /**
     * 获取单据号
     * @param moduleName
     * @return
     */
    CommonResult<String> getReceipts(String moduleName);
}
