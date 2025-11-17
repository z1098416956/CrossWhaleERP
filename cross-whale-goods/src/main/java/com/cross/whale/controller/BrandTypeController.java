package com.cross.whale.controller;

import com.cross.whale.common.CommonResult;
import com.cross.whale.req.CreateBrandTypeVO;
import com.cross.whale.req.DeleteBrandTypeVO;
import com.cross.whale.req.UpdateBrandTypeVO;
import com.cross.whale.res.BrandTypeInfoVO;
import com.cross.whale.res.TreeNodeVO;
import com.cross.whale.service.BrandTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/brand/type")
public class BrandTypeController {

    @Autowired
    private BrandTypeService brandTypeService;

    /**
     * 创建品牌
     * @param createBrandTypeVO
     * @return
     */
    @PostMapping("/createBrandTypeInfo")
    public CommonResult createBrandTypeInfo(@RequestBody CreateBrandTypeVO createBrandTypeVO){

        return brandTypeService.createBrandTypeInfo(createBrandTypeVO);
    }

    /**
     * 更新品牌
     * @param updateBrandTypeVO
     * @return
     */
    @PostMapping("/updateBrandTypeInfo")
    public CommonResult updateBrandTypeInfo(@RequestBody UpdateBrandTypeVO updateBrandTypeVO){

        return brandTypeService.updateBrandTypeInfo(updateBrandTypeVO);
    }

    /**
     * 获取品牌详情
     * @param id
     * @return
     */
    @GetMapping("/getBrandTypeInfo")
    public CommonResult<BrandTypeInfoVO> getBrandTypeInfo(@RequestParam Long id){

        return brandTypeService.getBrandTypeInfo(id);
    }

    /**
     *
     * @return
     */
    @GetMapping("/getBrandTypeTree")
    public CommonResult<List<TreeNodeVO>> getBrandTypeTree(){

        return brandTypeService.getBrandTypeTree();
    }

    /**
     * 删除品牌
     * @param deleteBrandTypeVO
     * @return
     */
    @PostMapping("/deleteBrandTypeInfo")
    public CommonResult deleteBrandTypeInfo(@RequestBody DeleteBrandTypeVO deleteBrandTypeVO){

        return brandTypeService.deleteBrandTypeInfo(deleteBrandTypeVO);
    }
}
