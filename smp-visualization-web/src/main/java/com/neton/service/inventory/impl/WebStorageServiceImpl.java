package com.neton.service.inventory.impl;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.goods.StorageClient;
import com.neton.feign.system.SystemServiceClient;
import com.neton.req.CreateStorageVO;
import com.neton.req.QueryAccAccountVO;
import com.neton.req.QueryStorageVO;
import com.neton.req.UpdateStorageVO;
import com.neton.res.AccAccountVO;
import com.neton.res.StorageVO;
import com.neton.res.UserInfoVO;
import com.neton.service.inventory.WebStorageService;
import com.neton.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WebStorageServiceImpl implements WebStorageService {

    @Autowired
    private StorageClient storageClient;

    @Autowired
    private SystemServiceClient systemServiceClient;

    /**
     * 创建仓库
     *
     * @param createStorageVO
     * @return
     */
    @Override
    public CommonResult createStorageInfo(CreateStorageVO createStorageVO) {
        return storageClient.createStorageInfo(createStorageVO);
    }

    /**
     * 更新仓库
     *
     * @param updateStorageVO
     * @return
     */
    @Override
    public CommonResult updateStorageInfo(UpdateStorageVO updateStorageVO) {
        return storageClient.updateStorageInfo(updateStorageVO);
    }

    /**
     * 仓库列表
     *
     * @param queryStorageVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<StorageVO>> queryStoragePage(QueryStorageVO queryStorageVO) {
        CommonResult<PageUtil<StorageVO>> commonResult = storageClient.queryStoragePage(queryStorageVO);
        if (commonResult.getCode() != 0){
            return commonResult;
        }
        if (commonResult.getData().getPageList() == null || commonResult.getData().getPageList().isEmpty()){
            return commonResult;
        }
        List<Long> collect = commonResult.getData().getPageList().stream().map(StorageVO::getAdminId).collect(Collectors.toList());
        if (collect == null || collect.isEmpty()){
            return commonResult;
        }
        QueryAccAccountVO queryAccAccountVO = new QueryAccAccountVO();
        queryAccAccountVO.setIds(collect);
        CommonResult<List<AccAccountVO>> result = systemServiceClient.queryAccountInfoList(queryAccAccountVO);
        if (result.getCode() != 0){
            return CommonResult.error(result.getCode(),result.getMessage());
        }
        List<AccAccountVO> resultData = result.getData();
        Map<Long, List<AccAccountVO>> map = resultData.stream().collect(Collectors.groupingBy(AccAccountVO::getId));
        PageUtil<StorageVO> data = commonResult.getData();
        for (StorageVO vo : data.getPageList()){
            Date createTime = vo.getCreateTime();
            String string = DateUtils.formatDateToString(createTime, "yyyy-MM-dd HH:mm:ss");
            vo.setCreateTimeStr(string);
            if (vo.getAdminId() != null){
                List<AccAccountVO> accountVOS = map.get(vo.getAdminId());
                vo.setAdminName(accountVOS.get(0).getAccountName());
            }
        }
        return CommonResult.success(data);
    }

    /**
     * 仓库列表
     *
     * @param queryStorageVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<StorageVO>> queryStoragePage2(QueryStorageVO queryStorageVO) {


        return storageClient.queryStoragePage2(queryStorageVO);
    }

    /**
     * 仓库详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<StorageVO> getStorageDetailInfo(Long id) {
        CommonResult<StorageVO> detailInfo = storageClient.getStorageDetailInfo(id);
        if (detailInfo.getCode() != 0){
            return detailInfo;
        }
        Long adminId = detailInfo.getData().getAdminId();
        CommonResult<UserInfoVO> info = systemServiceClient.getUserInfoById(adminId);
        if (info.getCode() != 0){
            return CommonResult.error(info.getCode(),info.getMessage());
        }
        StorageVO data = detailInfo.getData();
        data.setAdminName(info.getData().getName());
        return CommonResult.success(data);
    }

    /**
     * 删除仓库
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult deleteStorageInfo(Long id) {
        return storageClient.deleteStorageInfo(id);
    }
}
