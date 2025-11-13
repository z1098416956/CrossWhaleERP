package com.neton.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neton.entity.ReceiptsInfoDO;
import com.neton.req.QueryReceiptsReqVO;
import com.neton.res.ReceiptsInfoPageResVO;
import org.apache.ibatis.annotations.Param;

public interface ReceiptsInfoDao extends BaseMapper<ReceiptsInfoDO> {

    IPage<ReceiptsInfoPageResVO> queryReceiptsPage(IPage<ReceiptsInfoPageResVO>page,@Param("params") QueryReceiptsReqVO queryReceiptsReqVO);
}
