package com.neton.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neton.entity.SystemUserRoleDO;
import com.neton.res.SystemRuleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemUserRoleDao extends BaseMapper<SystemUserRoleDO> {

    List<SystemRuleVO> getUserRoleInfo(@Param("userId") Long userId);

    default List<SystemUserRoleDO> querySystemUserRoleByUserId(Long userId){
        LambdaQueryWrapper<SystemUserRoleDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SystemUserRoleDO::getUserId,userId);
        lambdaQueryWrapper.eq(SystemUserRoleDO::getIsDeleted,0);
        return selectList(lambdaQueryWrapper);
    }
}
