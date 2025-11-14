package com.neton.service.base.impl;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.base.SettlementAccountClient;
import com.neton.req.CreateSettlementAccountReqVO;
import com.neton.req.QuerySettlementAccountReqVO;
import com.neton.req.UpdateSettlementAccountReqVO;
import com.neton.req.UpdateSettlementAccountStatusReqVO;
import com.neton.res.SettlementAccountDetailsResVO;
import com.neton.service.base.WebSettlementAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebSettlementAccountServiceImpl implements WebSettlementAccountService {

    @Autowired
    private SettlementAccountClient settlementAccountClient;
    /**
     * 添加账户
     *
     * @param createSettlementAccountReqVO
     * @return
     */
    @Override
    public CommonResult<Void> saveSettlementAccount(CreateSettlementAccountReqVO createSettlementAccountReqVO) {
        return settlementAccountClient.saveSettlementAccount(createSettlementAccountReqVO);
    }

    /**
     * 获取账户详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<SettlementAccountDetailsResVO> getSettlementAccountDetails(Long id) {
        return settlementAccountClient.getSettlementAccountDetails(id);
    }

    /**
     * 更新账户信息
     *
     * @param updateSettlementAccountReqVO
     * @return
     */
    @Override
    public CommonResult<Void> updateSettlementAccount(UpdateSettlementAccountReqVO updateSettlementAccountReqVO) {
        return settlementAccountClient.updateSettlementAccount(updateSettlementAccountReqVO);
    }

    /**
     * 删除账户
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<Void> deleteSettlementAccount(Long id) {
        return settlementAccountClient.deleteSettlementAccount(id);
    }

    /**
     * 设置账户状态
     *
     * @param updateSettlementAccountStatusReqVO
     * @return
     */
    @Override
    public CommonResult<Void> settingAccountStatus(UpdateSettlementAccountStatusReqVO updateSettlementAccountStatusReqVO) {
        return settlementAccountClient.settingAccountStatus(updateSettlementAccountStatusReqVO);
    }

    /**
     * 设置账户默认
     *
     * @param updateSettlementAccountStatusReqVO
     * @return
     */
    @Override
    public CommonResult<Void> settingAccountDefault(UpdateSettlementAccountStatusReqVO updateSettlementAccountStatusReqVO) {
        return settlementAccountClient.settingAccountDefault(updateSettlementAccountStatusReqVO);
    }

    /**
     * 账户列表
     *
     * @param querySettlementAccountReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<SettlementAccountDetailsResVO>> querySettlementAccountPage(QuerySettlementAccountReqVO querySettlementAccountReqVO) {
        return settlementAccountClient.querySettlementAccountPage(querySettlementAccountReqVO);
    }
}
