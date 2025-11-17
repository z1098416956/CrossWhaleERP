package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cross.whale.entity.ProvincesDO;
import com.cross.whale.mybatis.query.LambdaQueryWrapperX;

import java.util.List;

public interface ProvincesDao extends BaseMapper<ProvincesDO> {

    default List<ProvincesDO> queryProvincesList(){
        LambdaQueryWrapperX<ProvincesDO> lambdaQueryWrapperX = new LambdaQueryWrapperX<>();
        return selectList(lambdaQueryWrapperX);
    }
}
