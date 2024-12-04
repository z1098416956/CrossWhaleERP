package com.neton.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neton.entity.ProductTypeDO;
import com.neton.res.TreeNodeVO;

import java.util.List;

public interface ProductTypeDao extends BaseMapper<ProductTypeDO> {

    List<TreeNodeVO> getProductTypeList();
}
