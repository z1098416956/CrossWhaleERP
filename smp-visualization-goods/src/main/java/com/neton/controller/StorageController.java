package com.neton.controller;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.CreateStorageVO;
import com.neton.req.QueryStorageVO;
import com.neton.req.UpdateStorageVO;
import com.neton.res.StorageVO;
import com.neton.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 创建仓库
     * @param createStorageVO
     * @return
     */
    @PostMapping("/createStorageInfo")
    public CommonResult createStorageInfo(@RequestBody CreateStorageVO createStorageVO){

        return storageService.createStorageInfo(createStorageVO);
    }

    /**
     * 更新仓库
     * @param updateStorageVO
     * @return
     */
    @PostMapping("/updateStorageInfo")
    public CommonResult updateStorageInfo(@RequestBody UpdateStorageVO updateStorageVO){

        return storageService.updateStorageInfo(updateStorageVO);
    }

    /**
     * 仓库列表
     * @param queryStorageVO
     * @return
     */
    @PostMapping("/queryStoragePage")
    public CommonResult<PageUtil<StorageVO>> queryStoragePage(@RequestBody QueryStorageVO queryStorageVO){

        return storageService.queryStoragePage(queryStorageVO);
    }

    /**
     * 仓库列表
     * @param queryStorageVO
     * @return
     */
    @PostMapping("/queryStoragePage2")
    public CommonResult<PageUtil<StorageVO>> queryStoragePage2(@RequestBody QueryStorageVO queryStorageVO){

        return storageService.queryStoragePage2(queryStorageVO);
    }

    /**
     * 仓库详情
     * @param id
     * @return
     */
    @GetMapping("/getStorageDetailInfo")
    public CommonResult<StorageVO> getStorageDetailInfo(@RequestParam Long id){

        return storageService.getStorageDetailInfo(id);
    }

    /**
     * 删除仓库
     * @param id
     * @return
     */
    @GetMapping("/deleteStorageInfo")
    public CommonResult deleteStorageInfo(@RequestParam Long id){

        return storageService.deleteStorageInfo(id);
    }
}
