package com.neton.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neton.entity.CitiesDO;
import com.neton.mybatis.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CitiesDao extends BaseMapper<CitiesDO> {

    default List<CitiesDO> queryCitiesList(){
        LambdaQueryWrapperX<CitiesDO> lambdaQueryWrapperX = new LambdaQueryWrapperX<>();
        return selectList(lambdaQueryWrapperX);
    }

    List<CitiesDO> queryCitiesById(@Param("id") String id);
}
