package com.neton.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.entity.SettlementAccountDO;
import com.neton.req.CreateSettlementAccountReqVO;
import com.neton.req.QuerySettlementAccountReqVO;
import com.neton.req.UpdateSettlementAccountReqVO;
import com.neton.req.UpdateSettlementAccountStatusReqVO;
import com.neton.res.SettlementAccountDetailsResVO;

public interface SettlementAccountService extends IService<SettlementAccountDO> {

    /**
     * 添加账户
     * @param createSettlementAccountReqVO
     * @return
     */
    CommonResult<Void> saveSettlementAccount(CreateSettlementAccountReqVO createSettlementAccountReqVO);

    /**
     * 获取账户详情
     * @param id
     * @return
     */
    CommonResult<SettlementAccountDetailsResVO> getSettlementAccountDetails(Long id);

    /**
     * 更新账户信息
     * @param updateSettlementAccountReqVO
     * @return
     */
    CommonResult<Void> updateSettlementAccount(UpdateSettlementAccountReqVO updateSettlementAccountReqVO);

    /**
     * 删除账户
     * @param id
     * @return
     */
    CommonResult<Void> deleteSettlementAccount(Long id);

    /**
     * 设置账户状态
     * @param updateSettlementAccountStatusReqVO
     * @return
     */
    CommonResult<Void> settingAccountStatus(UpdateSettlementAccountStatusReqVO updateSettlementAccountStatusReqVO);

    /**
     * 设置账户默认
     * @param updateSettlementAccountStatusReqVO
     * @return
     */
    CommonResult<Void> settingAccountDefault(UpdateSettlementAccountStatusReqVO updateSettlementAccountStatusReqVO);

    /**
     * 账户列表
     * @param querySettlementAccountReqVO
     * @return
     */
    CommonResult<PageUtil<SettlementAccountDetailsResVO>> querySettlementAccountPage(QuerySettlementAccountReqVO querySettlementAccountReqVO);
}
