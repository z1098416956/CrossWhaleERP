package com.neton.feign.goods;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.FeignConfig;
import com.neton.req.CreateStorageVO;
import com.neton.req.QueryStorageVO;
import com.neton.req.UpdateStorageVO;
import com.neton.res.StorageVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "smp-goods-service" ,configuration = FeignConfig.class,contextId = "storage-service")
public interface StorageClient {

    /**
     * 创建仓库
     * @param createStorageVO
     * @return
     */
    @PostMapping("/v1/storage/createStorageInfo")
    public CommonResult createStorageInfo(@RequestBody CreateStorageVO createStorageVO);

    /**
     * 更新仓库
     * @param updateStorageVO
     * @return
     */
    @PostMapping("/v1/storage/updateStorageInfo")
    public CommonResult updateStorageInfo(@RequestBody UpdateStorageVO updateStorageVO);

    /**
     * 仓库列表
     * @param queryStorageVO
     * @return
     */
    @PostMapping("/v1/storage/queryStoragePage")
    public CommonResult<PageUtil<StorageVO>> queryStoragePage(@RequestBody QueryStorageVO queryStorageVO);

    /**
     * 仓库详情
     * @param id
     * @return
     */
    @GetMapping("/v1/storage/getStorageDetailInfo")
    public CommonResult<StorageVO> getStorageDetailInfo(@RequestParam Long id);

    /**
     * 删除仓库
     * @param id
     * @return
     */
    @GetMapping("/v1/storage/deleteStorageInfo")
    public CommonResult deleteStorageInfo(@RequestParam Long id);

}
