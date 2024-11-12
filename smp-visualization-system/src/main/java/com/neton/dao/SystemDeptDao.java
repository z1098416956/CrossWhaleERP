package com.neton.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neton.entity.SystemDeptDO;
import com.neton.req.QuerySystemDeptVO;
import com.neton.res.SystemDeptDetailsVO;
import com.neton.res.SystemDeptTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemDeptDao extends BaseMapper<SystemDeptDO> {

    List<SystemDeptTree> querySystemDeptAll();

    IPage<SystemDeptDetailsVO> getSystemDeptPage(IPage<SystemDeptDetailsVO> page,
                                                 @Param("params") QuerySystemDeptVO querySystemDeptVO);
}
