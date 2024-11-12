package com.neton.controller.system;

import com.neton.common.CommonResult;
import com.neton.req.CreateRoleMenuVO;
import com.neton.res.SystemRoleMenuVO;
import com.neton.service.system.WebSystemRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: TheSunshine
 * @create: 2024-11-12 18:08
 **/
@RestController
@RequestMapping("/v1/web/roleMenu")
public class WebSystemRoleMenuController {

    @Autowired
    private WebSystemRoleMenuService systemRoleMenuService;

    /**
     * 绑定角色与菜单
     * @param createRoleMenuVO
     * @return
     */
    @PostMapping("/createSystemRoleMenuInfo")
    public CommonResult createSystemRoleMenuInfo(@RequestBody CreateRoleMenuVO createRoleMenuVO){

        return systemRoleMenuService.createSystemRoleMenuInfo(createRoleMenuVO);
    }

    /**
     * 修改角色与菜单
     * @param createRoleMenuVO
     * @return
     */
    @PostMapping("/updateSystemRoleMenuInfo")
    public CommonResult updateSystemRoleMenuInfo(@RequestBody CreateRoleMenuVO createRoleMenuVO){

        return systemRoleMenuService.updateSystemRoleMenuInfo(createRoleMenuVO);
    }

    /**
     * 删除角色菜单
     * @param roleId
     * @return
     */
    @GetMapping("/deleteSystemRoleMenuInfo")
    public CommonResult deleteSystemRoleMenuInfo(@RequestParam Long roleId){

        return systemRoleMenuService.deleteSystemRoleMenuInfo(roleId);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteSystemRoleMenuInfoById")
    public CommonResult deleteSystemRoleMenuInfoById(Long id){

        return systemRoleMenuService.deleteSystemRoleMenuInfoById(id);
    }

    /**
     * 获取角色菜单
     * @param roleId
     * @return
     */
    @GetMapping("/getSystemRoleMenuInfo")
    public CommonResult<SystemRoleMenuVO> getSystemRoleMenuInfo(@RequestParam Long roleId){

        return systemRoleMenuService.getSystemRoleMenuInfo(roleId);
    }
}
