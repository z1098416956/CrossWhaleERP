package com.neton.service.system;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.CreateRoleVO;
import com.neton.req.CreateUserRoleVO;
import com.neton.req.QueryRoleVO;
import com.neton.req.UpdateRoleVO;
import com.neton.res.SystemRuleVO;

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
}
