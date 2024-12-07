package com.neton.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neton.entity.ProductTypeDO;
import com.neton.req.QueryProductTypeVO;
import com.neton.res.TreeNodeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductTypeDao extends BaseMapper<ProductTypeDO> {

    List<TreeNodeVO> getProductTypeList();

    IPage<TreeNodeVO> queryProductTypePage(@Param("params") QueryProductTypeVO queryProductTypeVO,
                                           IPage<TreeNodeVO> page);
}
