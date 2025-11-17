package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cross.whale.entity.AccAccountDO;
import com.cross.whale.mybatis.query.LambdaQueryWrapperX;
import com.cross.whale.req.QueryAccAccountVO;
import com.cross.whale.res.AccAccountVO;
import com.cross.whale.res.UserInfoVO;
import org.apache.commons.lang3.StringUtils;
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
    default List<AccAccountDO> queryAccountInfoByParams(String accountNo,String userName){
        LambdaQueryWrapperX<AccAccountDO> queryWrapperX = new LambdaQueryWrapperX<>();
        queryWrapperX.eq(StringUtils.isNotBlank(accountNo),AccAccountDO::getAccountNo,accountNo);
        queryWrapperX.eq(StringUtils.isNotBlank(userName),AccAccountDO::getAccountName,userName);
        return selectList(queryWrapperX);
    }

    List<AccAccountVO> queryAccountInfoList(@Param("params") QueryAccAccountVO queryAccAccountVO);

    IPage<UserInfoVO> queryUserInfoPage(IPage<UserInfoVO> page,
                                        @Param("params") QueryAccAccountVO queryAccAccountVO);
}
