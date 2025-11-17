package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cross.whale.entity.ProductTypeDO;
import com.cross.whale.req.QueryProductTypeVO;
import com.cross.whale.res.TreeNodeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductTypeDao extends BaseMapper<ProductTypeDO> {

    List<TreeNodeVO> getProductTypeList();

    IPage<TreeNodeVO> queryProductTypePage(@Param("params") QueryProductTypeVO queryProductTypeVO,
                                           IPage<TreeNodeVO> page);
}
