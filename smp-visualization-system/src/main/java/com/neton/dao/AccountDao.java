package com.neton.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neton.entity.AccAccountDO;
import com.neton.mybatis.query.LambdaQueryWrapperX;
import com.neton.req.QueryAccAccountVO;
import com.neton.res.AccAccountVO;
import com.neton.res.UserInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author TheSunshine
 * @date 2024-10-31 10:17:02
 */
public interface AccountDao extends BaseMapper<AccAccountDO> {

    default List<AccAccountDO> queryAccountInfo(String accountNo){
        LambdaQueryWrapperX<AccAccountDO> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(AccAccountDO::getAccountNo,accountNo);
        return selectList(queryWrapperX);
    }

    List<AccAccountVO> queryAccountInfoList(@Param("params") QueryAccAccountVO queryAccAccountVO);

    IPage<UserInfoVO> queryUserInfoPage(IPage<UserInfoVO> page,
                                        @Param("params") QueryAccAccountVO queryAccAccountVO);
}
