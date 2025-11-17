package com.cross.whale.controller;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateSettlementAccountReqVO;
import com.cross.whale.req.QuerySettlementAccountReqVO;
import com.cross.whale.req.UpdateSettlementAccountReqVO;
import com.cross.whale.req.UpdateSettlementAccountStatusReqVO;
import com.cross.whale.res.SettlementAccountDetailsResVO;
import com.cross.whale.service.SettlementAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/settlement/account")
public class SettlementAccountController {

    @Autowired
    private SettlementAccountService settlementAccountService;

    /**
     * 添加账户
     * @param createSettlementAccountReqVO
     * @return
     */
    @PostMapping("/saveSettlementAccount")
    public CommonResult<Void> saveSettlementAccount(@RequestBody CreateSettlementAccountReqVO createSettlementAccountReqVO){
        return settlementAccountService.saveSettlementAccount(createSettlementAccountReqVO);
    }

    /**
     * 获取账户详情
     * @param id
     * @return
     */
    @GetMapping("/getSettlementAccountDetails")
    public CommonResult<SettlementAccountDetailsResVO> getSettlementAccountDetails(@RequestParam Long id){
        return settlementAccountService.getSettlementAccountDetails(id);
     }
    /**
     * 更新账户信息
     * @param updateSettlementAccountReqVO
     * @return
     */
    @PutMapping("/updateSettlementAccount")
    public CommonResult<Void> updateSettlementAccount(@RequestBody UpdateSettlementAccountReqVO updateSettlementAccountReqVO){
        return settlementAccountService.updateSettlementAccount(updateSettlementAccountReqVO);
    }

    /**
     * 删除账户
     * @param id
     * @return
     */
    @DeleteMapping("/deleteSettlementAccount")
    public CommonResult<Void> deleteSettlementAccount(@RequestParam Long id){
        return settlementAccountService.deleteSettlementAccount(id);
    }

    /**
     * 设置账户状态
     * @param updateSettlementAccountStatusReqVO
     * @return
     */
    @PutMapping("/settingAccountStatus")
    public CommonResult<Void> settingAccountStatus(@RequestBody UpdateSettlementAccountStatusReqVO updateSettlementAccountStatusReqVO){
        return settlementAccountService.settingAccountStatus(updateSettlementAccountStatusReqVO);
    }

    /**
     * 设置账户默认
     * @param updateSettlementAccountStatusReqVO
     * @return
     */
    @PostMapping("/settingAccountDefault")
    public CommonResult<Void> settingAccountDefault(@RequestBody UpdateSettlementAccountStatusReqVO updateSettlementAccountStatusReqVO){
        return settlementAccountService.settingAccountDefault(updateSettlementAccountStatusReqVO);
    }

    /**
     * 账户列表
     * @param querySettlementAccountReqVO
     * @return
     */
    @PostMapping("/querySettlementAccountPage")
    public CommonResult<PageUtil<SettlementAccountDetailsResVO>> querySettlementAccountPage(@RequestBody QuerySettlementAccountReqVO querySettlementAccountReqVO){
        return settlementAccountService.querySettlementAccountPage(querySettlementAccountReqVO);
    }
}
