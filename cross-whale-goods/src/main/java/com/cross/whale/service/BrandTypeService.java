package com.cross.whale.service;

import com.cross.whale.common.CommonResult;
import com.cross.whale.req.CreateBrandTypeVO;
import com.cross.whale.req.DeleteBrandTypeVO;
import com.cross.whale.req.UpdateBrandTypeVO;
import com.cross.whale.res.BrandTypeInfoVO;
import com.cross.whale.res.TreeNodeVO;

import java.util.List;

public interface BrandTypeService {

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
