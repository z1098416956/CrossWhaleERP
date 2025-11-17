package com.cross.whale.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.common.SystemErrorCodeConstants;
import com.cross.whale.dao.CitiesDao;
import com.cross.whale.dao.StorageDao;
import com.cross.whale.entity.CitiesDO;
import com.cross.whale.entity.StorageDO;
import com.cross.whale.req.CreateStorageVO;
import com.cross.whale.req.QueryStorageVO;
import com.cross.whale.req.UpdateStorageVO;
import com.cross.whale.res.StorageVO;
import com.cross.whale.service.StorageService;
import com.cross.whale.utils.SecurityUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Resource
    private CitiesDao citiesDao;


    /**
     * 创建仓库
     *
     * @param createStorageVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult createStorageInfo(CreateStorageVO createStorageVO) {
        if (StringUtils.isBlank(createStorageVO.getStorageName())){
            return CommonResult.error(SystemErrorCodeConstants.STORAGE_NAME_IS_NULL);
        }
        if (createStorageVO.getAdminId() == null){
            return CommonResult.error(SystemErrorCodeConstants.STORAGE_NAME_ADMIN_IS_NULL);
        }
        if (createStorageVO.getCityId() == null){
            return CommonResult.error(SystemErrorCodeConstants.STORAGE_CITY_ID_IS_NULL);
        }
        StorageDO storageDO = new StorageDO();
        BeanUtils.copyProperties(createStorageVO,storageDO);
        storageDO.setCreateBy(SecurityUtils.getUserId());
        storageDO.setIsDeleted(0);
        storageDO.setCreateTime(LocalDateTime.now());
        storageDO.setCreateByName(SecurityUtils.getUsername());
        storageDao.insert(storageDO);
        return CommonResult.success();
    }

    /**
     * 更新仓库
     *
     * @param updateStorageVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult updateStorageInfo(UpdateStorageVO updateStorageVO) {
        if (updateStorageVO.getId() == null){
            return CommonResult.error(SystemErrorCodeConstants.STORAGE_ID_IS_NULL);
        }
        StorageDO storageDO = storageDao.selectById(updateStorageVO.getId());
        if (storageDO == null){
            return CommonResult.error(SystemErrorCodeConstants.STORAGE_ID_IS_ERR);
        }
        storageDO.setAdminId(updateStorageVO.getAdminId());
        storageDO.setStorageName(updateStorageVO.getStorageName());
        storageDO.setCityId(updateStorageVO.getCityId());
        storageDO.setUpdateBy(SecurityUtils.getUserId());
        storageDO.setUpdateTime(LocalDateTime.now());
        storageDO.setUpdateByName(SecurityUtils.getUsername());
        storageDao.updateById(storageDO);
        return CommonResult.success();
    }

    /**
     * 仓库列表
     *
     * @param queryStorageVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<StorageVO>> queryStoragePage(QueryStorageVO queryStorageVO) {
        IPage<StorageVO> page = new Page<>();
        page.setCurrent(queryStorageVO.getPage());
        page.setSize(queryStorageVO.getSize());
        if (queryStorageVO.getCityId() != null){
            List<CitiesDO> list = citiesDao.queryCitiesById(queryStorageVO.getCityId());
            List<String> cityIds = new ArrayList<>();
            if (!list.isEmpty()){
                cityIds = list.stream().map(CitiesDO::getCityId).collect(Collectors.toList());
            }
            cityIds.add(queryStorageVO.getCityId());
            queryStorageVO.setCityIds(cityIds);
        }
        IPage<StorageVO> iPage = storageDao.queryStoragePage(page, queryStorageVO);
        PageUtil<StorageVO> pageUtil = new PageUtil<>();
        pageUtil.setPageList(iPage.getRecords());
        pageUtil.setTotal(iPage.getTotal());
        return CommonResult.success(pageUtil);
    }

    /**
     * 仓库列表
     *
     * @param queryStorageVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<StorageVO>> queryStoragePage2(QueryStorageVO queryStorageVO) {
        IPage<StorageVO> page = new Page<>();
        page.setCurrent(queryStorageVO.getPage());
        page.setSize(queryStorageVO.getSize());
        IPage<StorageVO> iPage = storageDao.queryStoragePage2(page, queryStorageVO);
        PageUtil<StorageVO> pageUtil = new PageUtil<>();
        pageUtil.setPageList(iPage.getRecords());
        pageUtil.setTotal(iPage.getTotal());
        return CommonResult.success(pageUtil);
    }

    /**
     * 仓库详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<StorageVO> getStorageDetailInfo(Long id) {
        if (id == null){
            return CommonResult.error(SystemErrorCodeConstants.STORAGE_ID_IS_NULL);
        }
        StorageDO storageDO = storageDao.selectById(id);
        if (storageDO == null){
            return CommonResult.error(SystemErrorCodeConstants.STORAGE_ID_IS_ERR);
        }
        StorageVO storageVO = new StorageVO();
        BeanUtils.copyProperties(storageDO,storageVO);
        return CommonResult.success(storageVO);
    }

    /**
     * 删除仓库
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult deleteStorageInfo(Long id) {
        if (id == null){
            return CommonResult.error(SystemErrorCodeConstants.STORAGE_ID_IS_NULL);
        }
        StorageDO storageDO = storageDao.selectById(id);
        if (storageDO == null){
            return CommonResult.error(SystemErrorCodeConstants.STORAGE_ID_IS_ERR);
        }
        storageDao.deleteById(storageDO);
        return CommonResult.success();
    }
}
