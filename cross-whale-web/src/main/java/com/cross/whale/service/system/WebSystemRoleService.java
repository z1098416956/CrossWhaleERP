package com.cross.whale.service.system;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateRoleVO;
import com.cross.whale.req.CreateUserRoleVO;
import com.cross.whale.req.QueryRoleVO;
import com.cross.whale.req.UpdateRoleVO;
import com.cross.whale.res.SystemRuleVO;

import java.util.List;

/**
 * @author: TheSunshine
 * @create: 2024-11-13 11:03
 **/
public interface WebSystemRoleService {

    /**
     * 创建系统角色
     * @param createRoleVO
     * @return
     */
    public CommonResult createSystemRole(CreateRoleVO createRoleVO);

    /**
     * 更新系统角色
     * @param updateRoleVO
     * @return
     */
    public CommonResult updateSystemRole(UpdateRoleVO updateRoleVO);

    /**
     * 删除系统角色
     * @param id
     * @return
     */
    public CommonResult deleteSystemRole(Long id);


    /**
     * 系统角色详情
     * @param id
     * @return
     */
    public CommonResult<SystemRuleVO> getSystemRoleDetails(Long id);

    /**
     * 系统角色列表
     * @return
     */
    public CommonResult<List<SystemRuleVO>> getSystemRoleList(QueryRoleVO queryRoleVO);

    /**
     * 系统角色分页
     * @param queryRoleVO
     * @return
     */
    public CommonResult<PageUtil<SystemRuleVO>> querySystemRolePage(QueryRoleVO queryRoleVO);

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
