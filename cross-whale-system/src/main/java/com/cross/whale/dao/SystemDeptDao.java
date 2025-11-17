package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cross.whale.entity.SystemDeptDO;
import com.cross.whale.req.QuerySystemDeptVO;
import com.cross.whale.res.SystemDeptDetailsVO;
import com.cross.whale.res.SystemDeptTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemDeptDao extends BaseMapper<SystemDeptDO> {

    List<SystemDeptTree> querySystemDeptAll();

    IPage<SystemDeptDetailsVO> getSystemDeptPage(IPage<SystemDeptDetailsVO> page,
                                                 @Param("params") QuerySystemDeptVO querySystemDeptVO);
}
