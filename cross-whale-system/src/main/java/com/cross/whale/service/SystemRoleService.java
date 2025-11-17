package com.cross.whale.service;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateRoleVO;
import com.cross.whale.req.QueryRoleVO;
import com.cross.whale.req.UpdateRoleVO;
import com.cross.whale.res.SystemRuleVO;

import java.util.List;

public interface SystemRoleService {

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
}
