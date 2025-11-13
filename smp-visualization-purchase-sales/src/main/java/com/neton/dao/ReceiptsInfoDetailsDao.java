package com.neton.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neton.entity.ReceiptsInfoDetailsDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReceiptsInfoDetailsDao extends BaseMapper<ReceiptsInfoDetailsDO> {

    default List<ReceiptsInfoDetailsDO> listReceiptsInfoDetails(Long receiptsId){
        LambdaQueryWrapper<ReceiptsInfoDetailsDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ReceiptsInfoDetailsDO::getReceiptsId, receiptsId);
        return selectList(wrapper);
    }

    void deleteReceiptsInfoDetailsByreceiptsId(@Param("receiptsId") Long receiptsId);
}
