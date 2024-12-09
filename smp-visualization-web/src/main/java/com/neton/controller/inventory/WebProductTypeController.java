package com.neton.controller.inventory;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.CreateProductTypeVO;
import com.neton.req.DeleteProductTypeVO;
import com.neton.req.QueryProductTypeVO;
import com.neton.req.UpdateProductTypeVO;
import com.neton.res.ProductTypeDetailsVO;
import com.neton.res.TreeNodeVO;
import com.neton.service.inventory.WebProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/v1/product/type")
public class WebProductTypeController {

    @Autowired
    private WebProductTypeService productTypeService;

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
