package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cross.whale.entity.AccAccountDO;
import com.cross.whale.mybatis.query.LambdaQueryWrapperX;

import java.util.List;

/**
 * @author TheSunshine
 * @date 2024-10-31 10:17:02
 */
public interface AccAccountDao extends BaseMapper<AccAccountDO> {

    default List<AccAccountDO> queryAccountInfo(String accountNo){
        LambdaQueryWrapperX<AccAccountDO> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(AccAccountDO::getAccountNo,accountNo);
        return selectList(queryWrapperX);
    }
}
