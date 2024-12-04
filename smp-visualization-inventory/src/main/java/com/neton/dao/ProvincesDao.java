package com.neton.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neton.entity.ProvincesDO;
import com.neton.mybatis.query.LambdaQueryWrapperX;

import java.util.List;

public interface ProvincesDao extends BaseMapper<ProvincesDO> {

    default List<ProvincesDO> queryProvincesList(){
        LambdaQueryWrapperX<ProvincesDO> lambdaQueryWrapperX = new LambdaQueryWrapperX<>();
        return selectList(lambdaQueryWrapperX);
    }
}
