package com.neton.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neton.entity.SystemMenuDO;
import com.neton.req.QuerySystemMenuVO;
import com.neton.res.SystemMenuDetailsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemMenuDao extends BaseMapper<SystemMenuDO> {

    List<SystemMenuDetailsVO> getSystemMenuList(@Param("params") QuerySystemMenuVO querySystemMenuVO);
}
