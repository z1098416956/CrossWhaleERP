package com.neton.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neton.entity.SettlementAccountDO;
import com.neton.mybatis.query.LambdaQueryWrapperX;
import com.neton.req.QuerySettlementAccountReqVO;
import com.neton.res.SettlementAccountDetailsResVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SettlementAccountDao extends BaseMapper<SettlementAccountDO> {

    /**
     * 查询当前是否有默认付款账户
     * @return
     */
    default List<SettlementAccountDO> getSettlementAccountDefault() {
        LambdaQueryWrapperX<SettlementAccountDO> wrapperX = new LambdaQueryWrapperX<>();
        wrapperX.eq(SettlementAccountDO::getIsDefault,0);
        return selectList(wrapperX);
    }

    /**
     * 分页付款账户
     * @param iPage
     * @param querySettlementAccountReqVO
     * @return
     */
    IPage<SettlementAccountDetailsResVO> querySettlementAccountPage(IPage<SettlementAccountDetailsResVO> iPage,@Param("params") QuerySettlementAccountReqVO querySettlementAccountReqVO);
}
