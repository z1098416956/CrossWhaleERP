package com.neton.service;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.CreateStorageVO;
import com.neton.req.QueryStorageVO;
import com.neton.req.UpdateStorageVO;
import com.neton.res.StorageVO;

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
