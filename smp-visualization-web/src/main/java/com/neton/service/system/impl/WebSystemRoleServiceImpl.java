package com.neton.service.system.impl;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.system.SystemServiceClient;
import com.neton.req.CreateRoleVO;
import com.neton.req.CreateUserRoleVO;
import com.neton.req.QueryRoleVO;
import com.neton.req.UpdateRoleVO;
import com.neton.res.SystemRuleVO;
import com.neton.service.system.WebSystemRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: TheSunshine
 * @create: 2024-11-13 11:03
 **/
@Service
@Slf4j
public class WebSystemRoleServiceImpl implements WebSystemRoleService {

    @Autowired
    private SystemServiceClient systemServiceClient;

    /**
     * 创建系统角色
     *
     * @param createRoleVO
     * @return
     */
    @Override
    public CommonResult createSystemRole(CreateRoleVO createRoleVO) {
        return systemServiceClient.createSystemRole(createRoleVO);
    }

    /**
     * 更新系统角色
     *
     * @param updateRoleVO
     * @return
     */
    @Override
    public CommonResult updateSystemRole(UpdateRoleVO updateRoleVO) {
        return systemServiceClient.updateSystemRole(updateRoleVO);
    }

    /**
     * 删除系统角色
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult deleteSystemRole(Long id) {
        return systemServiceClient.deleteSystemRole(id);
    }

    /**
     * 系统角色详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<SystemRuleVO> getSystemRoleDetails(Long id) {
        return systemServiceClient.getSystemRoleDetails(id);
    }

    /**
     * 系统角色列表
     *
     * @param queryRoleVO
     * @return
     */
    @Override
    public CommonResult<List<SystemRuleVO>> getSystemRoleList(QueryRoleVO queryRoleVO) {
        return systemServiceClient.getSystemRoleList(queryRoleVO);
    }

    /**
     * 系统角色分页
     *
     * @param queryRoleVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<SystemRuleVO>> querySystemRolePage(QueryRoleVO queryRoleVO) {
        return systemServiceClient.querySystemRolePage(queryRoleVO);
    }

    /**
     * 获取用户角色
     *
     * @return
     */
    @Override
    public CommonResult<List<SystemRuleVO>> getUserRoleInfo(Long id) {
        return systemServiceClient.getUserRoleInfo(id);
    }

    /**
     * 创建用户权限
     *
     * @param createUserRoleVO
     * @return
     */
    @Override
    public CommonResult createUserRoleInfo(CreateUserRoleVO createUserRoleVO) {
        return systemServiceClient.createUserRoleInfo(createUserRoleVO);
    }

    /**
     * 获取条码
     *
     * @param moduleName
     * @return
     */
    @Override
    public CommonResult<String> getBarcode(String moduleName) {
        return systemServiceClient.getBarcode(moduleName);
    }
}
