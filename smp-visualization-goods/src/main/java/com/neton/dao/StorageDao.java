package com.neton.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neton.entity.StorageDO;
import com.neton.req.QueryStorageVO;
import com.neton.res.StorageVO;
import org.apache.ibatis.annotations.Param;

public interface StorageDao extends BaseMapper<StorageDO> {

    IPage<StorageVO> queryStoragePage(IPage<StorageVO> page,
                                      @Param("params") QueryStorageVO queryStorageVO);


    IPage<StorageVO> queryStoragePage2(IPage<StorageVO> page,
                                      @Param("params") QueryStorageVO queryStorageVO);
}
