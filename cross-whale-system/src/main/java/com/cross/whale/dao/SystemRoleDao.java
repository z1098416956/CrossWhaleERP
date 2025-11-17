package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cross.whale.entity.SystemRoleDO;
import com.cross.whale.mybatis.query.LambdaQueryWrapperX;
import com.cross.whale.req.QueryRoleVO;
import com.cross.whale.res.SystemRuleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemRoleDao extends BaseMapper<SystemRoleDO> {

    List<SystemRuleVO> getSystemRoleList(@Param("params") QueryRoleVO queryRoleVO);

    IPage<SystemRuleVO> querySystemRolePage(IPage<SystemRuleVO> page,
                                            @Param("params") QueryRoleVO queryRoleVO);

    default List<SystemRoleDO> querySystemRoleInfo(String roleCode){
        LambdaQueryWrapperX<SystemRoleDO> lambdaQueryWrapperX = new LambdaQueryWrapperX<>();
        lambdaQueryWrapperX.eq(SystemRoleDO::getRoleCode,roleCode);
        return selectList(lambdaQueryWrapperX);
    }
}
