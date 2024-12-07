package com.neton.feign.inventory;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.FeignConfig;
import com.neton.req.CreateProductTypeVO;
import com.neton.req.QueryProductTypeVO;
import com.neton.req.UpdateProductTypeVO;
import com.neton.res.ProductTypeDetailsVO;
import com.neton.res.TreeNodeVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "smp-inventory-service" ,configuration = FeignConfig.class,contextId = "inventory-service")
public interface InventoryClient {


    /**
     * 创建产品类型
     * @param createProductTypeVO
     * @return
     */
    @PostMapping("/v1/product/type/createProductTypeInfo")
    public CommonResult createProductTypeInfo(@RequestBody CreateProductTypeVO createProductTypeVO);

    /**
     * 更新产品类型
     * @param updateProductTypeVO
     * @return
     */
    @PostMapping("/v1/product/type/updateProductTypeInfo")
    public CommonResult updateProductTypeInfo(@RequestBody UpdateProductTypeVO updateProductTypeVO);

    /**
     * 获取分类详情
     * @param id
     * @return
     */
    @GetMapping("/v1/product/type/getProductTypeDetails")
    public CommonResult<ProductTypeDetailsVO> getProductTypeDetails(@RequestParam Long id);


    /**
     * 删除分类详情
     * @param id
     * @return
     */
    @GetMapping("/v1/product/type/deleteProductTypeInfo")
    public CommonResult deleteProductTypeInfo(@RequestParam Long id);


    /**
     *
     * @return
     */
    @GetMapping("/v1/product/type/getProductTypeList")
    public CommonResult<List<TreeNodeVO>> getProductTypeList();

    /**
     * 分类列表
     * @param queryProductTypeVO
     * @return
     */
    @PostMapping("/v1/product/type/queryProductTypePage")
    public CommonResult<PageUtil<TreeNodeVO>> queryProductTypePage(@RequestBody QueryProductTypeVO queryProductTypeVO);
}
