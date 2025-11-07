package com.neton.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neton.entity.GoodsMultiAttributeDO;
import com.neton.req.QueryGoodsMultiAttributeReqVO;
import com.neton.res.GoodsMultiAttributeResVO;
import org.apache.ibatis.annotations.Param;

public interface GoodsMultiAttributeDao extends BaseMapper<GoodsMultiAttributeDO> {

    IPage<GoodsMultiAttributeResVO>  queryGoodsMultiAttributePage(IPage<GoodsMultiAttributeResVO> page, @Param("reqVO") QueryGoodsMultiAttributeReqVO reqVO);
}
