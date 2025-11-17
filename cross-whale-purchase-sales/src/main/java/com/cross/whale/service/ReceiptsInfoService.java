package com.cross.whale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.entity.ReceiptsInfoDO;
import com.cross.whale.req.*;
import com.cross.whale.res.ReceiptsInfoPageResVO;
import com.cross.whale.res.ReceiptsInfoResVO;

public interface ReceiptsInfoService extends IService<ReceiptsInfoDO> {

    /**
     * 添加请购单
     * @param createReceiptsInfoReqVO
     * @return
     */
    CommonResult<Void> createReceiptsInfo(CreateReceiptsInfoReqVO createReceiptsInfoReqVO);


    /**
     * 获取请购单详情
     * @param id
     * @return
     */
    CommonResult<ReceiptsInfoResVO> getReceiptsDetailsInfo(Long id);


    /**
     * 删除请购单
     * @param id
     * @return
     */
    CommonResult<Void> deleteReceiptsInfo(Long id);

    /**
     * 更新请购单状态
     * @param updateReceiptsStatusReqVO
     * @return
     */
    CommonResult<Void> updateReceiptsStatus(UpdateReceiptsStatusReqVO updateReceiptsStatusReqVO);


    /**
     *删除单据
     * @param deleteReceiptsReqVO
     * @return
     */
    CommonResult<Void> deleteReceipts(DeleteReceiptsReqVO deleteReceiptsReqVO);


    /**
     * 请购单列表
     * @param queryReceiptsReqVO
     * @return
     */
    CommonResult<PageUtil<ReceiptsInfoPageResVO>> queryReceiptsPage(QueryReceiptsReqVO queryReceiptsReqVO);

    /**
     * 更新单据信息
     * @param updateReceiptsInfoReqVO
     * @return
     */
    CommonResult<Void> updateReceiptsInfo(UpdateReceiptsInfoReqVO updateReceiptsInfoReqVO);
}
