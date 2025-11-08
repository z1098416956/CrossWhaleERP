package com.neton.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.common.SystemErrorCodeConstants;
import com.neton.dao.ProductTypeDao;
import com.neton.entity.ProductTypeDO;
import com.neton.req.CreateProductTypeVO;
import com.neton.req.DeleteProductTypeVO;
import com.neton.req.QueryProductTypeVO;
import com.neton.req.UpdateProductTypeVO;
import com.neton.res.ProductTypeDetailsVO;
import com.neton.res.TreeNodeVO;
import com.neton.service.ProductTypeService;
import com.neton.utils.SecurityUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ProductTypeServiceImpl implements ProductTypeService {

    @Resource
    private ProductTypeDao productTypeDao;

    /**
     * 创建产品类型
     *
     * @param createProductTypeVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult createProductTypeInfo(CreateProductTypeVO createProductTypeVO) {
        if (createProductTypeVO.getPid() == null){
            return CommonResult.error(SystemErrorCodeConstants.PRODUCT_TYPE_PID_IS_NULL);
        }
        if (createProductTypeVO.getPid() != null && createProductTypeVO.getPid().compareTo(0L) != 0){
            ProductTypeDO productTypeDO = productTypeDao.selectById(createProductTypeVO.getPid());
            if (productTypeDO == null){
                return CommonResult.error(SystemErrorCodeConstants.PRODUCT_TYPE_PID_IS_ERR);
            }
        }
        ProductTypeDO productTypeDO = new ProductTypeDO();
        productTypeDO.setTypeName(createProductTypeVO.getTypeName());
        productTypeDO.setPId(createProductTypeVO.getPid());
        productTypeDO.setCreateBy(SecurityUtils.getUserId());
        productTypeDO.setRemark(createProductTypeVO.getRemark());
        productTypeDO.setIsDeleted(0);
        productTypeDO.setCreateByName(SecurityUtils.getUsername());
        productTypeDO.setCreateTime(LocalDateTime.now());
        productTypeDao.insert(productTypeDO);
        return CommonResult.success();
    }

    /**
     * 更新产品类型
     *
     * @param updateProductTypeVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult updateProductTypeInfo(UpdateProductTypeVO updateProductTypeVO) {
        if (updateProductTypeVO.getId() == null){
            return CommonResult.error(SystemErrorCodeConstants.PRODUCT_TYPE_ID_IS_NULL);
        }
        if (updateProductTypeVO.getPid() != null && updateProductTypeVO.getPid().compareTo(0L) > 0){
            ProductTypeDO productTypeDO = productTypeDao.selectById(updateProductTypeVO.getPid());
            if (productTypeDO == null){
                return CommonResult.error(SystemErrorCodeConstants.PRODUCT_TYPE_PID_IS_ERR);
            }
        }
        ProductTypeDO productTypeDO = new ProductTypeDO();
        productTypeDO.setId(updateProductTypeVO.getId());
        productTypeDO.setRemark(updateProductTypeVO.getRemark());
        productTypeDO.setPId(updateProductTypeVO.getPid());
        productTypeDO.setTypeName(updateProductTypeVO.getTypeName());
        productTypeDO.setUpdateBy(SecurityUtils.getUserId());
        productTypeDO.setUpdateByName(SecurityUtils.getUsername());
        productTypeDO.setUpdateTime(LocalDateTime.now());
        productTypeDao.updateById(productTypeDO);
        return CommonResult.success();
    }

    /**
     * 获取分类详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<ProductTypeDetailsVO> getProductTypeDetails(Long id) {
        if (id == null){
            return CommonResult.error(SystemErrorCodeConstants.PRODUCT_TYPE_ID_IS_NULL);
        }
        ProductTypeDO productTypeDO = productTypeDao.selectById(id);
        if (productTypeDO == null){
            return CommonResult.error(SystemErrorCodeConstants.PRODUCT_TYPE_PID_IS_ERR);
        }
        ProductTypeDetailsVO productTypeDetailsVO = new ProductTypeDetailsVO();
        productTypeDetailsVO.setTypeName(productTypeDO.getTypeName());
        productTypeDetailsVO.setPId(productTypeDO.getPId());
        productTypeDetailsVO.setRemark(productTypeDO.getRemark());
        productTypeDetailsVO.setId(productTypeDO.getId());
        return CommonResult.success(productTypeDetailsVO);
    }

    /**
     * 删除分类详情
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult deleteProductTypeInfo(Long id) {
        if (id == null){
            return CommonResult.error(SystemErrorCodeConstants.PRODUCT_TYPE_ID_IS_NULL);
        }
        ProductTypeDO productTypeDO = productTypeDao.selectById(id);
        if (productTypeDO == null){
            return CommonResult.error(SystemErrorCodeConstants.PRODUCT_TYPE_PID_IS_ERR);
        }
        productTypeDao.deleteById(id);
        return CommonResult.success();
    }

    /**
     * @return
     */
    @Override
    public CommonResult<List<TreeNodeVO>> getProductTypeList() {
        List<TreeNodeVO> list = productTypeDao.getProductTypeList();
        return CommonResult.success(list);
    }

    /**
     * 分类列表
     *
     * @param queryProductTypeVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<TreeNodeVO>> queryProductTypePage(QueryProductTypeVO queryProductTypeVO) {
        IPage<TreeNodeVO> page = new Page<>();
        page.setCurrent(queryProductTypeVO.getPage());
        page.setSize(queryProductTypeVO.getSize());
        IPage<TreeNodeVO> iPage = productTypeDao.queryProductTypePage(queryProductTypeVO, page);
        PageUtil<TreeNodeVO> pageUtil = new PageUtil<>();
        pageUtil.setPageList(iPage.getRecords());
        pageUtil.setTotal(iPage.getTotal());
        return CommonResult.success(pageUtil);
    }

    /**
     * 删除分类
     *
     * @param deleteProductTypeVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult deleteProductTypeIds(DeleteProductTypeVO deleteProductTypeVO) {
        if (deleteProductTypeVO.getIds() == null || deleteProductTypeVO.getIds().isEmpty()){
            return CommonResult.error(SystemErrorCodeConstants.PRODUCT_TYPE_ID_IS_NULL);
        }
        productTypeDao.deleteByIds(deleteProductTypeVO.getIds());
        return CommonResult.success();
    }
}
