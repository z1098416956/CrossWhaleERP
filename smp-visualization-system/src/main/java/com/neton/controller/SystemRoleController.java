package com.neton.controller;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.CreateRoleVO;
import com.neton.req.CreateUserRoleVO;
import com.neton.req.QueryRoleVO;
import com.neton.req.UpdateRoleVO;
import com.neton.res.SystemRuleVO;
import com.neton.service.SystemRoleService;
import com.neton.service.SystemUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: TheSunshine
 * @create: 2024-11-13 10:46
 **/
@RestController
@RequestMapping("/v1/system/role")
public class SystemRoleController {

    @Autowired
    private SystemRoleService systemRoleService;
    @Autowired
    private SystemUserRoleService systemUserRoleService;

    /**
     * 创建系统角色
     * @param createRoleVO
     * @return
     */
    @RequestMapping(value = "/createSystemRole",method = RequestMethod.POST)
    public CommonResult createSystemRole(@RequestBody CreateRoleVO createRoleVO){

        return systemRoleService.createSystemRole(createRoleVO);
    }

    /**
     * 更新系统角色
     * @param updateRoleVO
     * @return
     */
    @PostMapping("/updateSystemRole")
    public CommonResult updateSystemRole(@RequestBody UpdateRoleVO updateRoleVO){

        return systemRoleService.updateSystemRole(updateRoleVO);
    }

    /**
     * 删除系统角色
     * @param id
     * @return
     */
    @GetMapping("/deleteSystemRole")
    public CommonResult deleteSystemRole(@RequestParam Long id){

        return systemRoleService.deleteSystemRole(id);
    }


    /**
     * 系统角色详情
     * @param id
     * @return
     */
    @GetMapping("/getSystemRoleDetails")
    public CommonResult<SystemRuleVO> getSystemRoleDetails(@RequestParam Long id){

        return systemRoleService.getSystemRoleDetails(id);
    }

    /**
     * 系统角色列表
     * @return
     */
    @PostMapping("/getSystemRoleList")
    public CommonResult<List<SystemRuleVO>> getSystemRoleList(@RequestBody QueryRoleVO queryRoleVO){

        return systemRoleService.getSystemRoleList(queryRoleVO);
    }


    @GetMapping("/getUserRoleInfo")
    public CommonResult<List<SystemRuleVO>> getUserRoleInfo(@RequestParam Long id){

        return systemUserRoleService.getUserRoleInfo(id);
    }

    /**
     * 系统角色分页
     * @param queryRoleVO
     * @return
     */
    @PostMapping("/querySystemRolePage")
    public CommonResult<PageUtil<SystemRuleVO>> querySystemRolePage(@RequestBody QueryRoleVO queryRoleVO){

        return systemRoleService.querySystemRolePage(queryRoleVO);
    }

    /**
     * 分配用户角色
     * @param createUserRoleVO
     * @return
     */
    @PostMapping("/createUserRoleInfo")
    public CommonResult createUserRoleInfo(@RequestBody CreateUserRoleVO createUserRoleVO){

        return systemUserRoleService.createUserRoleInfo(createUserRoleVO);
    }


    /**
     * 获取条码
     * @param moduleName
     * @return
     */
    @GetMapping("/getBarcode")
    public CommonResult<String> getBarcode(@RequestParam String moduleName){
        return systemUserRoleService.getBarcode(moduleName);
    }
}
