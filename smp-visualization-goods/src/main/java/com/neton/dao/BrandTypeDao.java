package com.neton.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neton.entity.BrandTypeDO;
import com.neton.res.TreeNodeVO;

import java.util.List;

public interface BrandTypeDao extends BaseMapper<BrandTypeDO> {

    List<TreeNodeVO> queryBrandTypeTree();
}
