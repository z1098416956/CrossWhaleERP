package com.cross.whale.controller;

import com.cross.whale.common.CommonResult;
import com.cross.whale.req.CreateSystemMenuVO;
import com.cross.whale.req.QuerySystemMenuVO;
import com.cross.whale.req.UpdateSystemMenuVO;
import com.cross.whale.res.SystemMenuDetailsVO;
import com.cross.whale.service.SystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: TheSunshine
 * @create: 2024-11-12 15:51
 **/
@RestController
@RequestMapping("/v1/system/menu")
public class SystemMenuController {

    @Autowired
    private SystemMenuService systemMenuService;

    /**
     * 创建菜单
     * @param createSystemMenuVO
     * @return
     */
    @PostMapping("/createSystemMenu")
    public CommonResult createSystemMenu(@RequestBody CreateSystemMenuVO createSystemMenuVO){

        return systemMenuService.createSystemMenu(createSystemMenuVO);
    }

    /**
     * 更新菜单
     * @param updateSystemMenuVO
     * @return
     */
    @PostMapping("/updateSystemMenu")
    public CommonResult updateSystemMenu(@RequestBody UpdateSystemMenuVO updateSystemMenuVO){

        return systemMenuService.updateSystemMenu(updateSystemMenuVO);
    }


    /**
     * 菜单详情
     * @param id
     * @return
     */
    @GetMapping("/getSystemMenuDetail")
    public CommonResult<SystemMenuDetailsVO> getSystemMenuDetail(@RequestParam Long id){

        return systemMenuService.getSystemMenuDetail(id);
    }

    /**
     * 菜单列表
     * @param querySystemMenuVO
     * @return
     */
    @PostMapping("/getSystemMenuList")
    public CommonResult<List<SystemMenuDetailsVO>> getSystemMenuList(@RequestBody QuerySystemMenuVO querySystemMenuVO){

        return systemMenuService.getSystemMenuList(querySystemMenuVO);
    }

    /**
     * 删除菜单
     * @param id
     * @return
     */
    @GetMapping("/deletedSystemMenuInfo")
    public CommonResult deletedSystemMenuInfo(@RequestParam Long id){

        return systemMenuService.deletedSystemMenuInfo(id);
    }
}
