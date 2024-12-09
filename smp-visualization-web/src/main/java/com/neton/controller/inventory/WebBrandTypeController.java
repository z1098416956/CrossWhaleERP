package com.neton.controller.inventory;

import com.neton.common.CommonResult;
import com.neton.req.CreateBrandTypeVO;
import com.neton.req.DeleteBrandTypeVO;
import com.neton.req.UpdateBrandTypeVO;
import com.neton.res.BrandTypeInfoVO;
import com.neton.res.TreeNodeVO;
import com.neton.service.inventory.WebBrandTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/v1/brand/type")
public class WebBrandTypeController {

    @Autowired
    private WebBrandTypeService brandTypeService;

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
