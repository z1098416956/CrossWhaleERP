package com.cross.whale.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cross.whale.entity.ReceiptsInfoDetailsDO;
import com.cross.whale.req.UpdateReceiptsInfoReqVO;
import com.cross.whale.res.ReceiptsInfoDetailsResVO;

import java.util.List;

public interface ReceiptsInfoDetailsService extends IService<ReceiptsInfoDetailsDO> {

    /**
     * 获取请购单详细信息
     * @param receiptsId
     * @return
     */
    List<ReceiptsInfoDetailsResVO> listReceiptsInfoDetails(Long receiptsId);

    /**
     * 删除请购单商品信息
     * @param receiptsId
     */
    void deleteReceiptsInfoDetails(Long receiptsId);

    /**
     * 更新单据详情信息
     * @param updateReceiptsInfoReqVO
     */
    void updateReceiptsInfoDetails(UpdateReceiptsInfoReqVO updateReceiptsInfoReqVO);
}
