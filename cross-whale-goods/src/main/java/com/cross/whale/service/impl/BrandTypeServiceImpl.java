package com.cross.whale.service.impl;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.SystemErrorCodeConstants;
import com.cross.whale.dao.BrandTypeDao;
import com.cross.whale.entity.BrandTypeDO;
import com.cross.whale.req.CreateBrandTypeVO;
import com.cross.whale.req.DeleteBrandTypeVO;
import com.cross.whale.req.UpdateBrandTypeVO;
import com.cross.whale.res.BrandTypeInfoVO;
import com.cross.whale.res.TreeNodeVO;
import com.cross.whale.service.BrandTypeService;
import com.cross.whale.utils.SecurityUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class BrandTypeServiceImpl implements BrandTypeService {

    @Resource
    private BrandTypeDao brandTypeDao;

    /**
     * 创建品牌
     *
     * @param createBrandTypeVO
     * @return
     */
    @Override
    public CommonResult createBrandTypeInfo(CreateBrandTypeVO createBrandTypeVO) {
        if (StringUtils.isBlank(createBrandTypeVO.getBrandName())){
            return CommonResult.error(SystemErrorCodeConstants.BRAND_NAME_IS_NULL);
        }
        if (createBrandTypeVO.getPid() == null){
            return CommonResult.error(SystemErrorCodeConstants.BRAND_IPD_IS_NULL);
        }
        if (createBrandTypeVO.getPid() != null && createBrandTypeVO.getPid().compareTo(0L) != 0){
            BrandTypeDO brandTypeDO = brandTypeDao.selectById(createBrandTypeVO.getPid());
            if (brandTypeDO == null){
                return CommonResult.error(SystemErrorCodeConstants.BRAND_IPD_IS_ERR);
            }
        }
        BrandTypeDO brandTypeDO = new BrandTypeDO();
        brandTypeDO.setBrandName(createBrandTypeVO.getBrandName());
        brandTypeDO.setRemark(createBrandTypeVO.getRemark());
        brandTypeDO.setPId(createBrandTypeVO.getPid());
        brandTypeDO.setIsDeleted(0);
        brandTypeDO.setCreateBy(SecurityUtils.getUserId());
        brandTypeDO.setCreateTime(LocalDateTime.now());
        brandTypeDO.setCreateByName(SecurityUtils.getUsername());
        brandTypeDao.insert(brandTypeDO);
        return CommonResult.success();
    }

    /**
     * 更新品牌
     *
     * @param updateBrandTypeVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult updateBrandTypeInfo(UpdateBrandTypeVO updateBrandTypeVO) {
        if (updateBrandTypeVO.getId() == null){
            return CommonResult.error(SystemErrorCodeConstants.BRAND_ID_IS_ERR);
        }
        if (StringUtils.isBlank(updateBrandTypeVO.getBrandName())){
            return CommonResult.error(SystemErrorCodeConstants.BRAND_NAME_IS_NULL);
        }
        if (updateBrandTypeVO.getPid() == null){
            return CommonResult.error(SystemErrorCodeConstants.BRAND_IPD_IS_NULL);
        }
        if (updateBrandTypeVO.getPid() != null && updateBrandTypeVO.getPid().compareTo(0L) != 0){
            BrandTypeDO brandTypeDO = brandTypeDao.selectById(updateBrandTypeVO.getPid());
            if (brandTypeDO == null){
                return CommonResult.error(SystemErrorCodeConstants.BRAND_IPD_IS_ERR);
            }
        }
        BrandTypeDO brandTypeDO = new BrandTypeDO();
        brandTypeDO.setBrandName(updateBrandTypeVO.getBrandName());
        brandTypeDO.setRemark(updateBrandTypeVO.getRemark());
        brandTypeDO.setPId(updateBrandTypeVO.getPid());
        brandTypeDO.setUpdateBy(SecurityUtils.getUserId());
        brandTypeDO.setUpdateTime(LocalDateTime.now());
        brandTypeDO.setUpdateByName(SecurityUtils.getUsername());
        brandTypeDO.setId(updateBrandTypeVO.getId());
        brandTypeDao.updateById(brandTypeDO);
        return CommonResult.success();
    }

    /**
     * 获取品牌详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<BrandTypeInfoVO> getBrandTypeInfo(Long id) {
        if (id == null){
            return CommonResult.error(SystemErrorCodeConstants.BRAND_ID_IS_ERR);
        }
        BrandTypeDO brandTypeDO = brandTypeDao.selectById(id);
        if (brandTypeDO == null){
            return CommonResult.error(SystemErrorCodeConstants.BRAND_IPD_IS_ERR);
        }
        BrandTypeInfoVO vo = new BrandTypeInfoVO();
        vo.setPid(brandTypeDO.getPId());
        vo.setBrandName(brandTypeDO.getBrandName());
        vo.setRemark(brandTypeDO.getRemark());
        vo.setId(brandTypeDO.getId());
        return CommonResult.success(vo);
    }

    /**
     * @return
     */
    @Override
    public CommonResult<List<TreeNodeVO>> getBrandTypeTree() {
        List<TreeNodeVO> list = brandTypeDao.queryBrandTypeTree();
        return CommonResult.success(list);
    }

    /**
     * 删除品牌
     *
     * @param deleteBrandTypeVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult deleteBrandTypeInfo(DeleteBrandTypeVO deleteBrandTypeVO) {
        if (deleteBrandTypeVO.getIds() == null || deleteBrandTypeVO.getIds().isEmpty()){
            return CommonResult.error(SystemErrorCodeConstants.BRAND_ID_IS_ERR);
        }
        brandTypeDao.deleteByIds(deleteBrandTypeVO.getIds());
        return CommonResult.success();
    }
}
