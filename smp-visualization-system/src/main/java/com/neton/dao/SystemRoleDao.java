package com.neton.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neton.entity.SystemRoleDO;
import com.neton.mybatis.query.LambdaQueryWrapperX;
import com.neton.req.QueryRoleVO;
import com.neton.res.SystemRuleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemRoleDao extends BaseMapper<SystemRoleDO> {

    List<SystemRuleVO> getSystemRoleList(@Param("params") QueryRoleVO queryRoleVO);

    default List<SystemRoleDO> querySystemRoleInfo(String roleCode){
        LambdaQueryWrapperX<SystemRoleDO> lambdaQueryWrapperX = new LambdaQueryWrapperX<>();
        lambdaQueryWrapperX.eq(SystemRoleDO::getRoleCode,roleCode);
        return selectList(lambdaQueryWrapperX);
    }
}
