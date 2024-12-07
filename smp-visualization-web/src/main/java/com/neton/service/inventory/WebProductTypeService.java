package com.neton.service.inventory;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.CreateProductTypeVO;
import com.neton.req.QueryProductTypeVO;
import com.neton.req.UpdateProductTypeVO;
import com.neton.res.ProductTypeDetailsVO;
import com.neton.res.TreeNodeVO;

import java.util.List;

public interface WebProductTypeService {

    /**
     * 创建产品类型
     * @param createProductTypeVO
     * @return
     */
    public CommonResult createProductTypeInfo(CreateProductTypeVO createProductTypeVO);

    /**
     * 更新产品类型
     * @param updateProductTypeVO
     * @return
     */
    public CommonResult updateProductTypeInfo(UpdateProductTypeVO updateProductTypeVO);

    /**
     * 获取分类详情
     * @param id
     * @return
     */
    public CommonResult<ProductTypeDetailsVO> getProductTypeDetails(Long id);


    /**
     * 删除分类详情
     * @param id
     * @return
     */
    public CommonResult deleteProductTypeInfo(Long id);


    /**
     *
     * @return
     */
    public CommonResult<List<TreeNodeVO>> getProductTypeList();

    /**
     * 分类列表
     * @param queryProductTypeVO
     * @return
     */
    public CommonResult<PageUtil<TreeNodeVO>> queryProductTypePage(QueryProductTypeVO queryProductTypeVO);
}
