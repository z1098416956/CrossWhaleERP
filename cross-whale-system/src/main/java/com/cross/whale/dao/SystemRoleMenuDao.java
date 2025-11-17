package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cross.whale.entity.SystemRoleMenuDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemRoleMenuDao extends BaseMapper<SystemRoleMenuDO> {

    void deleteByRoleId(@Param("roleId") Long roleId);

    void deleteById(@Param("id") Long id);

    List<Long> querySystemRoleMenuInfo(@Param("roleId") Long roleId);
}
