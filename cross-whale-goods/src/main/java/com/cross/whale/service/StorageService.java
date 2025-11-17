package com.cross.whale.service;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateStorageVO;
import com.cross.whale.req.QueryStorageVO;
import com.cross.whale.req.UpdateStorageVO;
import com.cross.whale.res.StorageVO;

public interface StorageService {

    /**
     * 创建仓库
     * @param createStorageVO
     * @return
     */
    public CommonResult createStorageInfo(CreateStorageVO createStorageVO);

    /**
     * 更新仓库
     * @param updateStorageVO
     * @return
     */
    public CommonResult updateStorageInfo(UpdateStorageVO updateStorageVO);

    /**
     * 仓库列表
     * @param queryStorageVO
     * @return
     */
    public CommonResult<PageUtil<StorageVO>> queryStoragePage(QueryStorageVO queryStorageVO);

    /**
     * 仓库列表
     * @param queryStorageVO
     * @return
     */
    public CommonResult<PageUtil<StorageVO>> queryStoragePage2(QueryStorageVO queryStorageVO);
    /**
     * 仓库详情
     * @param id
     * @return
     */
    public CommonResult<StorageVO> getStorageDetailInfo(Long id);

    /**
     * 删除仓库
     * @param id
     * @return
     */
    public CommonResult deleteStorageInfo(Long id);
}
