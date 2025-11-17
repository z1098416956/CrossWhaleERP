package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cross.whale.entity.BrandTypeDO;
import com.cross.whale.res.TreeNodeVO;

import java.util.List;

public interface BrandTypeDao extends BaseMapper<BrandTypeDO> {

    List<TreeNodeVO> queryBrandTypeTree();
}
