package com.neton.service.inventory;

import com.neton.common.CommonResult;
import com.neton.req.CreateBrandTypeVO;
import com.neton.req.DeleteBrandTypeVO;
import com.neton.req.UpdateBrandTypeVO;
import com.neton.res.BrandTypeInfoVO;
import com.neton.res.TreeNodeVO;

import java.util.List;

public interface WebBrandTypeService {

    /**
     * 创建品牌
     * @param createBrandTypeVO
     * @return
     */
    public CommonResult createBrandTypeInfo(CreateBrandTypeVO createBrandTypeVO);

    /**
     * 更新品牌
     * @param updateBrandTypeVO
     * @return
     */
    public CommonResult updateBrandTypeInfo(UpdateBrandTypeVO updateBrandTypeVO);

    /**
     * 获取品牌详情
     * @param id
     * @return
     */
    public CommonResult<BrandTypeInfoVO> getBrandTypeInfo(Long id);

    /**
     *
     * @return
     */
    public CommonResult<List<TreeNodeVO>> getBrandTypeTree();

    /**
     * 删除品牌
     * @param deleteBrandTypeVO
     * @return
     */
    public CommonResult deleteBrandTypeInfo(DeleteBrandTypeVO deleteBrandTypeVO);
}
