package com.cross.whale.controller;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateSystemDeptVO;
import com.cross.whale.req.QuerySystemDeptVO;
import com.cross.whale.req.UpdateSystemDeptVO;
import com.cross.whale.res.SystemDeptDetailsVO;
import com.cross.whale.res.SystemDeptTree;
import com.cross.whale.service.SystemDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: TheSunshine
 * @create: 2024-11-12 13:55
 **/
@RestController
@RequestMapping("/v1/system/dept")
public class SystemDeptController {

    @Autowired
    private SystemDeptService systemDeptService;


    /**
     * 创建系统部门
     * @param createSystemDeptVO
     * @return
     */
    @RequestMapping(value = "/createSystemDeptInfo",method = RequestMethod.POST)
    public CommonResult createSystemDeptInfo(@RequestBody CreateSystemDeptVO createSystemDeptVO){

        return systemDeptService.createSystemDeptInfo(createSystemDeptVO);
    }

    /**
     * 更新系统部门
     * @param updateSystemDeptVO
     * @return
     */
    @RequestMapping(value = "/updateSystemDeptInfo",method = RequestMethod.POST)
    public CommonResult updateSystemDeptInfo(@RequestBody UpdateSystemDeptVO updateSystemDeptVO){

        return systemDeptService.updateSystemDeptInfo(updateSystemDeptVO);
    }

    /**
     * 系统部门详情
     * @param id
     * @return
     */
    @GetMapping("/getSystemDeptDetailsInfo")
    public CommonResult<SystemDeptDetailsVO> getSystemDeptDetailsInfo(@RequestParam Long id){

        return systemDeptService.getSystemDeptDetailsInfo(id);
    }

    /**
     * 根据id获取当前这个id的tree
     * @param id
     * @return
     */
    @GetMapping("/getSystemDeptTree")
    public CommonResult<List<SystemDeptTree>> getSystemDeptTreeById(@RequestParam Long id){

        return systemDeptService.getSystemDeptTreeById(id);
    }

    /**
     * 查询部门信息分页
     * @param querySystemDeptVO
     * @return
     */
    @PostMapping("/getSystemDeptPage")
    public CommonResult<PageUtil<SystemDeptDetailsVO>> getSystemDeptPage(@RequestBody QuerySystemDeptVO querySystemDeptVO){

        return systemDeptService.getSystemDeptPage(querySystemDeptVO);
    }

    /**
     * 删除部门信息
     * @param id
     * @return
     */
    @GetMapping("/deleteSystemDeptInfo")
    public CommonResult deleteSystemDeptInfo(@RequestParam Long id){

        return systemDeptService.deleteSystemDeptInfo(id);
    }
}
