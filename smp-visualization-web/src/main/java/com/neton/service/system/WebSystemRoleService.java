package com.neton.service.system;

import com.neton.common.CommonResult;
import com.neton.req.CreateRoleVO;
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
}
