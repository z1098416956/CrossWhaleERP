package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cross.whale.entity.StorageDO;
import com.cross.whale.req.QueryStorageVO;
import com.cross.whale.res.StorageVO;
import org.apache.ibatis.annotations.Param;

public interface StorageDao extends BaseMapper<StorageDO> {

    IPage<StorageVO> queryStoragePage(IPage<StorageVO> page,
                                      @Param("params") QueryStorageVO queryStorageVO);


    IPage<StorageVO> queryStoragePage2(IPage<StorageVO> page,
                                      @Param("params") QueryStorageVO queryStorageVO);
}
