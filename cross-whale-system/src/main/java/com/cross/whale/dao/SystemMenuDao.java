package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cross.whale.entity.SystemMenuDO;
import com.cross.whale.req.QuerySystemMenuVO;
import com.cross.whale.res.SystemMenuDetailsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemMenuDao extends BaseMapper<SystemMenuDO> {

    List<SystemMenuDetailsVO> getSystemMenuList(@Param("params") QuerySystemMenuVO querySystemMenuVO);
}
