package com.neton.feign.base;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.FeignConfig;
import com.neton.req.CreateSettlementAccountReqVO;
import com.neton.req.QuerySettlementAccountReqVO;
import com.neton.req.UpdateSettlementAccountReqVO;
import com.neton.req.UpdateSettlementAccountStatusReqVO;
import com.neton.res.SettlementAccountDetailsResVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "smp-base-service" ,configuration = FeignConfig.class,contextId = "base-settlement-service")
public interface SettlementAccountClient {

    /**
     * 添加账户
     *
     * @param createSettlementAccountReqVO
     * @return
     */
    @PostMapping("/v1/settlement/account/saveSettlementAccount")
    public CommonResult<Void> saveSettlementAccount(@RequestBody CreateSettlementAccountReqVO createSettlementAccountReqVO);

    /**
     * 获取账户详情
     *
     * @param id
     * @return
     */
    @GetMapping("/v1/settlement/account/getSettlementAccountDetails")
    public CommonResult<SettlementAccountDetailsResVO> getSettlementAccountDetails(@RequestParam Long id);

    /**
     * 更新账户信息
     *
     * @param updateSettlementAccountReqVO
     * @return
     */
    @PutMapping("/v1/settlement/account/updateSettlementAccount")
    public CommonResult<Void> updateSettlementAccount(@RequestBody UpdateSettlementAccountReqVO updateSettlementAccountReqVO);

    /**
     * 删除账户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/v1/settlement/account/deleteSettlementAccount")
    public CommonResult<Void> deleteSettlementAccount(@RequestParam Long id);

    /**
     * 设置账户状态
     *
     * @param updateSettlementAccountStatusReqVO
     * @return
     */
    @PutMapping("/v1/settlement/account/settingAccountStatus")
    public CommonResult<Void> settingAccountStatus(@RequestBody UpdateSettlementAccountStatusReqVO updateSettlementAccountStatusReqVO);

    /**
     * 设置账户默认
     *
     * @param updateSettlementAccountStatusReqVO
     * @return
     */
    @PostMapping("/v1/settlement/account/settingAccountDefault")
    public CommonResult<Void> settingAccountDefault(@RequestBody UpdateSettlementAccountStatusReqVO updateSettlementAccountStatusReqVO);

    /**
     * 账户列表
     *
     * @param querySettlementAccountReqVO
     * @return
     */
    @PostMapping("/v1/settlement/account/querySettlementAccountPage")
    public CommonResult<PageUtil<SettlementAccountDetailsResVO>> querySettlementAccountPage(@RequestBody QuerySettlementAccountReqVO querySettlementAccountReqVO);

}
