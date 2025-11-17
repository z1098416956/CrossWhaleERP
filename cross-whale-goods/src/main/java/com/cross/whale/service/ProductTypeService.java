package com.cross.whale.service;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateProductTypeVO;
import com.cross.whale.req.DeleteProductTypeVO;
import com.cross.whale.req.QueryProductTypeVO;
import com.cross.whale.req.UpdateProductTypeVO;
import com.cross.whale.res.ProductTypeDetailsVO;
import com.cross.whale.res.TreeNodeVO;

import java.util.List;

public interface ProductTypeService {

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

    /**
     * 删除分类
     * @param deleteProductTypeVO
     * @return
     */
    public CommonResult deleteProductTypeIds(DeleteProductTypeVO deleteProductTypeVO);
}
