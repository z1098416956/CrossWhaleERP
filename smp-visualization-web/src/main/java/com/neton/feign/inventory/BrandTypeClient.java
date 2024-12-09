package com.neton.feign.inventory;

import com.neton.common.CommonResult;
import com.neton.feign.FeignConfig;
import com.neton.req.CreateBrandTypeVO;
import com.neton.req.DeleteBrandTypeVO;
import com.neton.req.UpdateBrandTypeVO;
import com.neton.res.BrandTypeInfoVO;
import com.neton.res.TreeNodeVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "smp-inventory-service" ,configuration = FeignConfig.class,contextId = "brand-type-service")
public interface BrandTypeClient {

    /**
     * 创建品牌
     * @param createBrandTypeVO
     * @return
     */
    @PostMapping("/v1/brand/type/createBrandTypeInfo")
    public CommonResult createBrandTypeInfo(@RequestBody CreateBrandTypeVO createBrandTypeVO);

    /**
     * 更新品牌
     * @param updateBrandTypeVO
     * @return
     */
    @PostMapping("/v1/brand/type/updateBrandTypeInfo")
    public CommonResult updateBrandTypeInfo(@RequestBody UpdateBrandTypeVO updateBrandTypeVO);

    /**
     * 获取品牌详情
     * @param id
     * @return
     */
    @GetMapping("/v1/brand/type/getBrandTypeInfo")
    public CommonResult<BrandTypeInfoVO> getBrandTypeInfo(@RequestParam Long id);

    /**
     *
     * @return
     */
    @GetMapping("/v1/brand/type/getBrandTypeTree")
    public CommonResult<List<TreeNodeVO>> getBrandTypeTree();

    /**
     * 删除品牌
     * @param deleteBrandTypeVO
     * @return
     */
    @PostMapping("/v1/brand/type/deleteBrandTypeInfo")
    public CommonResult deleteBrandTypeInfo(@RequestBody DeleteBrandTypeVO deleteBrandTypeVO);
}
