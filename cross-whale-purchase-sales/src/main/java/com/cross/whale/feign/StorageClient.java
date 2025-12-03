package com.cross.whale.feign;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateStorageVO;
import com.cross.whale.req.QueryStorageVO;
import com.cross.whale.req.UpdateStorageVO;
import com.cross.whale.res.StorageVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cross-whale-goods" ,configuration = FeignConfig.class,contextId = "storage-service")
public interface StorageClient {



    /**
     * 仓库列表
     * @param queryStorageVO
     * @return
     */
    @PostMapping("/v1/storage/queryStoragePage")
    public CommonResult<PageUtil<StorageVO>> queryStoragePage(@RequestBody QueryStorageVO queryStorageVO);
    /**
     * 仓库列表
     * @param queryStorageVO
     * @return
     */
    @PostMapping("/v1/storage/queryStoragePage2")
    public CommonResult<PageUtil<StorageVO>> queryStoragePage2(@RequestBody QueryStorageVO queryStorageVO);

    /**
     * 仓库详情
     * @param id
     * @return
     */
    @GetMapping("/v1/storage/getStorageDetailInfo")
    public CommonResult<StorageVO> getStorageDetailInfo(@RequestParam Long id);



}
