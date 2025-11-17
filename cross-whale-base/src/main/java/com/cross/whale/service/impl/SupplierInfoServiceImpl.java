package com.cross.whale.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cross.whale.bean.NetonBeanUtils;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.common.SystemErrorCodeConstants;
import com.cross.whale.dao.SupplierInfoDao;
import com.cross.whale.entity.SupplierInfoDO;
import com.cross.whale.req.CreateSupplierInfoReqVO;
import com.cross.whale.req.QuerySupplierInfoReqVO;
import com.cross.whale.req.UpdateSupplierInfoReqVO;
import com.cross.whale.res.SupplierInfoDetailsResVO;
import com.cross.whale.service.SupplierInfoService;
import com.cross.whale.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupplierInfoServiceImpl extends ServiceImpl<SupplierInfoDao,SupplierInfoDO> implements SupplierInfoService {
    /**
     * 创建供应商信息
     *
     * @param createSupplierInfoReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> saveSupplierInfo(CreateSupplierInfoReqVO createSupplierInfoReqVO) {
        if (StringUtils.isBlank(createSupplierInfoReqVO.getSupplierName())) {
            return CommonResult.error(SystemErrorCodeConstants.BASE_SUPPLIER_NAME_NULL);
        }
        List<SupplierInfoDO> list = baseMapper.checkSupperName(createSupplierInfoReqVO.getSupplierName());
        if (list != null && !list.isEmpty()) {
            return CommonResult.error(SystemErrorCodeConstants.BASE_SUPPLIER_NAME_REPEAT);
        }
        SupplierInfoDO bean = NetonBeanUtils.toBean(createSupplierInfoReqVO, SupplierInfoDO.class);
        bean.setCreateBy(SecurityUtils.getUserId());
        bean.setUpdateBy(SecurityUtils.getUserId());
        bean.setIsDeleted(0);
        bean.setCreateTime(LocalDateTime.now());
        bean.setUpdateTime(LocalDateTime.now());
        bean.setCreateByName(SecurityUtils.getUsername());
        bean.setUpdateByName(SecurityUtils.getUsername());
        baseMapper.insert(bean);
        return CommonResult.success();
    }

    /**
     * 更新供应商信息
     *
     * @param updateSupplierInfoReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> updateSupplierInfo(UpdateSupplierInfoReqVO updateSupplierInfoReqVO) {
        if (StringUtils.isBlank(updateSupplierInfoReqVO.getSupplierName())) {
            return CommonResult.error(SystemErrorCodeConstants.BASE_SUPPLIER_NAME_NULL);
        }
        SupplierInfoDO infoDO = baseMapper.selectById(updateSupplierInfoReqVO.getId());
        if (infoDO == null) {
            return CommonResult.error(SystemErrorCodeConstants.BASE_SUPPLIER_IS_NULL);
        }
        List<SupplierInfoDO> list = baseMapper.checkSupperName(updateSupplierInfoReqVO.getSupplierName());
        if (list != null && !list.isEmpty() && list.size() > 2) {
            return CommonResult.error(SystemErrorCodeConstants.BASE_SUPPLIER_NAME_REPEAT);
        }

        if (list == null || list.isEmpty()) {
            SupplierInfoDO supplierInfoDO = baseMapper.selectById(updateSupplierInfoReqVO.getId());
            BeanUtils.copyProperties(updateSupplierInfoReqVO, supplierInfoDO);
            supplierInfoDO.setUpdateBy(SecurityUtils.getUserId());
            supplierInfoDO.setUpdateTime(LocalDateTime.now());
            supplierInfoDO.setUpdateByName(SecurityUtils.getUsername());
            baseMapper.updateById(supplierInfoDO);
        }else {
            SupplierInfoDO supplierInfoDO = list.get(0);
            if (supplierInfoDO.getId().longValue() != updateSupplierInfoReqVO.getId().longValue()) {
                return CommonResult.error(SystemErrorCodeConstants.BASE_SUPPLIER_NAME_REPEAT);
            }
            NetonBeanUtils.copyProperties(updateSupplierInfoReqVO, supplierInfoDO);
            supplierInfoDO.setUpdateBy(SecurityUtils.getUserId());
            supplierInfoDO.setUpdateTime(LocalDateTime.now());
            supplierInfoDO.setUpdateByName(SecurityUtils.getUsername());
            baseMapper.updateById(supplierInfoDO);
        }

        return CommonResult.success();
    }

    /**
     * 删除供应商
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> deleteSupplierInfo(Long id) {
        SupplierInfoDO supplierInfoDO = baseMapper.selectById(id);
        if (supplierInfoDO == null) {
            return CommonResult.error(SystemErrorCodeConstants.BASE_SUPPLIER_IS_NULL);
        }
        supplierInfoDO.setUpdateBy(SecurityUtils.getUserId());
        supplierInfoDO.setUpdateTime(LocalDateTime.now());
        supplierInfoDO.setUpdateByName(SecurityUtils.getUsername());
        supplierInfoDO.setIsDeleted(1);
        baseMapper.deleteById(supplierInfoDO);
        return CommonResult.success();
    }

    /**
     * 获取供应商信息
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<SupplierInfoDetailsResVO> getSupplierInfo(Long id) {
        SupplierInfoDO supplierInfoDO = baseMapper.selectById(id);
        if (supplierInfoDO == null) {
            return CommonResult.error(SystemErrorCodeConstants.BASE_SUPPLIER_IS_NULL);
        }
        SupplierInfoDetailsResVO bean = NetonBeanUtils.toBean(supplierInfoDO, SupplierInfoDetailsResVO.class);
        return CommonResult.success(bean);
    }

    /**
     * 供应商信息
     *
     * @param querySupplierInfoReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<SupplierInfoDetailsResVO>> listSupplierInfo(QuerySupplierInfoReqVO querySupplierInfoReqVO) {
        IPage<SupplierInfoDetailsResVO> page = new Page<>();
        page.setCurrent(querySupplierInfoReqVO.getPage());
        page.setSize(querySupplierInfoReqVO.getSize());
        IPage<SupplierInfoDetailsResVO> iPage = baseMapper.listSupplierInfo(page, querySupplierInfoReqVO);
        PageUtil<SupplierInfoDetailsResVO> pageUtil = new PageUtil<>();
        pageUtil.setPageList(iPage.getRecords());
        pageUtil.setTotal(iPage.getTotal());
        return CommonResult.success(pageUtil);
    }
}
