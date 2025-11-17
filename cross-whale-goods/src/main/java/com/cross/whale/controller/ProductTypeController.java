package com.cross.whale.controller;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateProductTypeVO;
import com.cross.whale.req.DeleteProductTypeVO;
import com.cross.whale.req.QueryProductTypeVO;
import com.cross.whale.req.UpdateProductTypeVO;
import com.cross.whale.res.ProductTypeDetailsVO;
import com.cross.whale.res.TreeNodeVO;
import com.cross.whale.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product/type")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    /**
     * 创建产品类型
     * @param createProductTypeVO
     * @return
     */
    @PostMapping("/createProductTypeInfo")
    public CommonResult createProductTypeInfo(@RequestBody CreateProductTypeVO createProductTypeVO){

        return productTypeService.createProductTypeInfo(createProductTypeVO);
    }

    /**
     * 更新产品类型
     * @param updateProductTypeVO
     * @return
     */
    @PostMapping("/updateProductTypeInfo")
    public CommonResult updateProductTypeInfo(@RequestBody UpdateProductTypeVO updateProductTypeVO){

        return productTypeService.updateProductTypeInfo(updateProductTypeVO);
    }

    /**
     * 获取分类详情
     * @param id
     * @return
     */
    @GetMapping("/getProductTypeDetails")
    public CommonResult<ProductTypeDetailsVO> getProductTypeDetails(@RequestParam Long id){

        return productTypeService.getProductTypeDetails(id);
    }


    /**
     * 删除分类详情
     * @param id
     * @return
     */
    @GetMapping("/deleteProductTypeInfo")
    public CommonResult deleteProductTypeInfo(@RequestParam Long id){

        return productTypeService.deleteProductTypeInfo(id);
    }


    /**
     *
     * @return
     */
    @GetMapping("/getProductTypeList")
    public CommonResult<List<TreeNodeVO>> getProductTypeList(){

        return productTypeService.getProductTypeList();
    }

    /**
     * 分类列表
     * @param queryProductTypeVO
     * @return
     */
    @PostMapping("/queryProductTypePage")
    public CommonResult<PageUtil<TreeNodeVO>> queryProductTypePage(@RequestBody QueryProductTypeVO queryProductTypeVO){

        return productTypeService.queryProductTypePage(queryProductTypeVO);
    }

    /**
     * 删除分类
     * @param deleteProductTypeVO
     * @return
     */
    @PostMapping("/deleteProductTypeIds")
    public CommonResult deleteProductTypeIds(@RequestBody DeleteProductTypeVO deleteProductTypeVO){

        return productTypeService.deleteProductTypeIds(deleteProductTypeVO);
    }
}
