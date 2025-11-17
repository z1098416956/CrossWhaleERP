package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cross.whale.entity.GoodsInfoDO;
import com.cross.whale.req.QueryGoodsInfoReqVO;
import com.cross.whale.res.GoodsInfoPageResVO;
import com.cross.whale.res.GoodsInfoReceiptsPageResVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsInfoDao extends BaseMapper<GoodsInfoDO> {

    /**
     * 检查商品名称是否重复
     * @param goodsName
     * @return
     */
    default List<GoodsInfoDO> checkGoodsNameRepeat(String goodsName) {
        LambdaQueryWrapper<GoodsInfoDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GoodsInfoDO::getGoodsName,goodsName);
        return selectList(queryWrapper);
    }

    /**
     * 商品分页
     * @param page
     * @param queryGoodsInfoReqVO
     * @return
     */
    IPage<GoodsInfoPageResVO> queryGoodsInfoPage(IPage<GoodsInfoPageResVO> page,@Param("params") QueryGoodsInfoReqVO queryGoodsInfoReqVO);

    /**
     * 采购单商品分页
     * @param page
     * @param queryGoodsInfoReqVO
     * @return
     */
    IPage<GoodsInfoReceiptsPageResVO> queryGoodsReceiptsPage(IPage<GoodsInfoReceiptsPageResVO> page,@Param("params") QueryGoodsInfoReqVO queryGoodsInfoReqVO);
}
