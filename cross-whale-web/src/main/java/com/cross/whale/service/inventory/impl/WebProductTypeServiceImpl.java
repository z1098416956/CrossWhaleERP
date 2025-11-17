package com.cross.whale.service.inventory.impl;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.feign.goods.InventoryClient;
import com.cross.whale.req.CreateProductTypeVO;
import com.cross.whale.req.DeleteProductTypeVO;
import com.cross.whale.req.QueryProductTypeVO;
import com.cross.whale.req.UpdateProductTypeVO;
import com.cross.whale.res.ProductTypeDetailsVO;
import com.cross.whale.res.TreeNodeVO;
import com.cross.whale.service.inventory.WebProductTypeService;
import com.cross.whale.utils.tree.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WebProductTypeServiceImpl implements WebProductTypeService {

    @Autowired
    private InventoryClient inventoryClient;
    /**
     * 创建产品类型
     *
     * @param createProductTypeVO
     * @return
     */
    @Override
    public CommonResult createProductTypeInfo(CreateProductTypeVO createProductTypeVO) {
        return inventoryClient.createProductTypeInfo(createProductTypeVO);
    }

    /**
     * 更新产品类型
     *
     * @param updateProductTypeVO
     * @return
     */
    @Override
    public CommonResult updateProductTypeInfo(UpdateProductTypeVO updateProductTypeVO) {
        return inventoryClient.updateProductTypeInfo(updateProductTypeVO);
    }

    /**
     * 获取分类详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<ProductTypeDetailsVO> getProductTypeDetails(Long id) {
        return inventoryClient.getProductTypeDetails(id);
    }

    /**
     * 删除分类详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult deleteProductTypeInfo(Long id) {
        return inventoryClient.deleteProductTypeInfo(id);
    }

    /**
     * @return
     */
    @Override
    public CommonResult<List<TreeNodeVO>> getProductTypeList() {
        CommonResult<List<TreeNodeVO>> commonResult = inventoryClient.getProductTypeList();
        List<TreeNodeVO> list = commonResult.getData();
        if (list.isEmpty()){
            return CommonResult.success(list);
        }
        List<TreeNodeVO> trees = TreeUtils.buildTree(list, 0l);
        return CommonResult.success(trees);
    }

    /**
     * 分类列表
     *
     * @param queryProductTypeVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<TreeNodeVO>> queryProductTypePage(QueryProductTypeVO queryProductTypeVO) {

        return inventoryClient.queryProductTypePage(queryProductTypeVO);
    }

    /**
     * 删除分类
     *
     * @param deleteProductTypeVO
     * @return
     */
    @Override
    public CommonResult deleteProductTypeIds(DeleteProductTypeVO deleteProductTypeVO) {
        return inventoryClient.deleteProductTypeIds(deleteProductTypeVO);
    }
}
