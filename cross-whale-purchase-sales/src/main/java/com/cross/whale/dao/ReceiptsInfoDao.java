package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cross.whale.entity.ReceiptsInfoDO;
import com.cross.whale.req.QueryReceiptsReqVO;
import com.cross.whale.res.ReceiptsInfoPageResVO;
import org.apache.ibatis.annotations.Param;

public interface ReceiptsInfoDao extends BaseMapper<ReceiptsInfoDO> {

    IPage<ReceiptsInfoPageResVO> queryReceiptsPage(IPage<ReceiptsInfoPageResVO>page,@Param("params") QueryReceiptsReqVO queryReceiptsReqVO);
}
