package com.neton.controller.system;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.CreateRoleVO;
import com.neton.req.CreateUserRoleVO;
import com.neton.req.QueryRoleVO;
import com.neton.req.UpdateRoleVO;
import com.neton.res.SystemRuleVO;
import com.neton.service.system.WebSystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: TheSunshine
 * @create: 2024-11-13 11:06
 **/
@RestController
@RequestMapping("/v1/web/system/role")
public class WebSystemRoleController {

    @Autowired
    private WebSystemRoleService systemRoleService;

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

    /**
     * 系统角色分页
     * @param queryRoleVO
     * @return
     */
    @PostMapping("/querySystemRolePage")
    public CommonResult<PageUtil<SystemRuleVO>> querySystemRolePage(@RequestBody QueryRoleVO queryRoleVO){

        return systemRoleService.querySystemRolePage(queryRoleVO);
    }

    @GetMapping("/getUserRoleInfo")
    public CommonResult<List<SystemRuleVO>> getUserRoleInfo(@RequestParam Long id){

        return systemRoleService.getUserRoleInfo(id);
    }

    /**
     * 分配用户角色
     * @param createUserRoleVO
     * @return
     */
    @PostMapping("/createUserRoleInfo")
    public CommonResult createUserRoleInfo(@RequestBody CreateUserRoleVO createUserRoleVO){

        return systemRoleService.createUserRoleInfo(createUserRoleVO);
    }


    /**
     * 获取条码
     * @param moduleName
     * @return
     */
    @GetMapping("/getBarcode")
    public CommonResult<String> getBarcode(@RequestParam String moduleName){
        return systemRoleService.getBarcode(moduleName);
    }

    /**
     * 获取条码
     * @param moduleName
     * @return
     */
    @GetMapping("/getReceipts")
    public CommonResult<String> getReceipts(@RequestParam String moduleName){
        return systemRoleService.getReceipts(moduleName);
    }

}
