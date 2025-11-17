package com.cross.whale.service.base;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateSettlementAccountReqVO;
import com.cross.whale.req.QuerySettlementAccountReqVO;
import com.cross.whale.req.UpdateSettlementAccountReqVO;
import com.cross.whale.req.UpdateSettlementAccountStatusReqVO;
import com.cross.whale.res.SettlementAccountDetailsResVO;

public interface WebSettlementAccountService {

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
