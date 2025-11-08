package com.neton.service.inventory.impl;

import com.neton.common.CommonResult;
import com.neton.feign.goods.BrandTypeClient;
import com.neton.req.CreateBrandTypeVO;
import com.neton.req.DeleteBrandTypeVO;
import com.neton.req.UpdateBrandTypeVO;
import com.neton.res.BrandTypeInfoVO;
import com.neton.res.TreeNodeVO;
import com.neton.service.inventory.WebBrandTypeService;
import com.neton.utils.tree.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WebBrandTypeServiceImpl implements WebBrandTypeService {

    @Autowired
    private BrandTypeClient brandTypeClient;

    /**
     * 创建品牌
     *
     * @param createBrandTypeVO
     * @return
     */
    @Override
    public CommonResult createBrandTypeInfo(CreateBrandTypeVO createBrandTypeVO) {
        return brandTypeClient.createBrandTypeInfo(createBrandTypeVO);
    }

    /**
     * 更新品牌
     *
     * @param updateBrandTypeVO
     * @return
     */
    @Override
    public CommonResult updateBrandTypeInfo(UpdateBrandTypeVO updateBrandTypeVO) {
        return brandTypeClient.updateBrandTypeInfo(updateBrandTypeVO);
    }

    /**
     * 获取品牌详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<BrandTypeInfoVO> getBrandTypeInfo(Long id) {
        return brandTypeClient.getBrandTypeInfo(id);
    }

    /**
     * @return
     */
    @Override
    public CommonResult<List<TreeNodeVO>> getBrandTypeTree() {
        CommonResult<List<TreeNodeVO>> tree = brandTypeClient.getBrandTypeTree();
        if (tree.getCode() != 0){
            return tree;
        }
        List<TreeNodeVO> data = tree.getData();
        if (data == null || data.isEmpty()){
            return tree;
        }
        List<TreeNodeVO> trees = TreeUtils.buildTree(data, 0l);
        return CommonResult.success(trees);
    }

    /**
     * 删除品牌
     *
     * @param deleteBrandTypeVO
     * @return
     */
    @Override
    public CommonResult deleteBrandTypeInfo(DeleteBrandTypeVO deleteBrandTypeVO) {
        return brandTypeClient.deleteBrandTypeInfo(deleteBrandTypeVO);
    }
}
