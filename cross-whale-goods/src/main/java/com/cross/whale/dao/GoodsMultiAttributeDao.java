package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cross.whale.entity.GoodsMultiAttributeDO;
import com.cross.whale.req.QueryGoodsMultiAttributeReqVO;
import com.cross.whale.res.GoodsMultiAttributeResVO;
import org.apache.ibatis.annotations.Param;

public interface GoodsMultiAttributeDao extends BaseMapper<GoodsMultiAttributeDO> {

    IPage<GoodsMultiAttributeResVO>  queryGoodsMultiAttributePage(IPage<GoodsMultiAttributeResVO> page, @Param("reqVO") QueryGoodsMultiAttributeReqVO reqVO);
}
