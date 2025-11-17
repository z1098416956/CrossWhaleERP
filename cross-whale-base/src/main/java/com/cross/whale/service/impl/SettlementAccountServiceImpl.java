package com.cross.whale.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cross.whale.bean.NetonBeanUtils;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.common.SystemErrorCodeConstants;
import com.cross.whale.dao.SettlementAccountDao;
import com.cross.whale.entity.SettlementAccountDO;
import com.cross.whale.req.CreateSettlementAccountReqVO;
import com.cross.whale.req.QuerySettlementAccountReqVO;
import com.cross.whale.req.UpdateSettlementAccountReqVO;
import com.cross.whale.req.UpdateSettlementAccountStatusReqVO;
import com.cross.whale.res.SettlementAccountDetailsResVO;
import com.cross.whale.service.SettlementAccountService;
import com.cross.whale.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SettlementAccountServiceImpl extends ServiceImpl<SettlementAccountDao, SettlementAccountDO> implements SettlementAccountService {



    /**
     * 添加账户
     *
     * @param createSettlementAccountReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> saveSettlementAccount(CreateSettlementAccountReqVO createSettlementAccountReqVO) {
        if (createSettlementAccountReqVO.getIsDefault() != null && createSettlementAccountReqVO.getIsDefault() == 0){
            List<SettlementAccountDO> isDefault  = baseMapper.getSettlementAccountDefault();
            if (isDefault != null && !isDefault.isEmpty()){
                return CommonResult.error(SystemErrorCodeConstants.BASE_ACCOUNT_IS_REPEAT);
            }
        }else {
            createSettlementAccountReqVO.setIsDefault(1);
        }
        SettlementAccountDO bean = NetonBeanUtils.toBean(createSettlementAccountReqVO, SettlementAccountDO.class);
        bean.setCreateBy(SecurityUtils.getUserId());
        bean.setUpdateBy(SecurityUtils.getUserId());
        bean.setCreateByName(SecurityUtils.getUsername());
        bean.setUpdateByName(SecurityUtils.getUsername());
        bean.setIsDeleted(0);
        bean.setCreateTime(LocalDateTime.now());
        bean.setUpdateTime(LocalDateTime.now());
        baseMapper.insert(bean);
        return CommonResult.success();
    }

    /**
     * 获取账户详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<SettlementAccountDetailsResVO> getSettlementAccountDetails(Long id) {
        SettlementAccountDO settlementAccountDO = baseMapper.selectById(id);
        if (settlementAccountDO == null){
            return CommonResult.error(SystemErrorCodeConstants.BASE_ACCOUNT_IS_NULL);
        }
        SettlementAccountDetailsResVO bean = NetonBeanUtils.toBean(settlementAccountDO, SettlementAccountDetailsResVO.class);
        return CommonResult.success(bean);
    }

    /**
     * 更新账户信息
     *
     * @param updateSettlementAccountReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> updateSettlementAccount(UpdateSettlementAccountReqVO updateSettlementAccountReqVO) {
        if (updateSettlementAccountReqVO.getId() == null){
            return CommonResult.error(SystemErrorCodeConstants.BASE_ACCOUNT_IS_ID);
        }
        if (updateSettlementAccountReqVO.getIsDefault() != null && updateSettlementAccountReqVO.getIsDefault() == 0){
            List<SettlementAccountDO> isDefault  = baseMapper.getSettlementAccountDefault();
            if (isDefault != null && !isDefault.isEmpty() && isDefault.get(0).getId().longValue() != updateSettlementAccountReqVO.getId().longValue()){
                return CommonResult.error(SystemErrorCodeConstants.BASE_ACCOUNT_IS_REPEAT);
            }
        }
        SettlementAccountDO settlementAccountDO = baseMapper.selectById(updateSettlementAccountReqVO.getId());
        if (settlementAccountDO == null){
            return CommonResult.error(SystemErrorCodeConstants.BASE_ACCOUNT_IS_NULL);
        }
        NetonBeanUtils.copyProperties(updateSettlementAccountReqVO, settlementAccountDO);
        settlementAccountDO.setUpdateBy(SecurityUtils.getUserId());
        settlementAccountDO.setUpdateByName(SecurityUtils.getUsername());
        settlementAccountDO.setUpdateTime(LocalDateTime.now());
        baseMapper.updateById(settlementAccountDO);
        return CommonResult.success();
    }

    /**
     * 删除账户
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> deleteSettlementAccount(Long id) {
        SettlementAccountDO settlementAccountDO = baseMapper.selectById(id);
        if (settlementAccountDO == null){
            return CommonResult.error(SystemErrorCodeConstants.BASE_ACCOUNT_IS_NULL);
        }
        settlementAccountDO.setUpdateBy(SecurityUtils.getUserId());
        settlementAccountDO.setUpdateByName(SecurityUtils.getUsername());
        settlementAccountDO.setUpdateTime(LocalDateTime.now());
        settlementAccountDO.setIsDeleted(1);
        baseMapper.deleteById(settlementAccountDO);
        return CommonResult.success();
    }

    /**
     * 设置账户状态
     *
     * @param updateSettlementAccountStatusReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> settingAccountStatus(UpdateSettlementAccountStatusReqVO updateSettlementAccountStatusReqVO) {
        List<SettlementAccountDO> settlementAccountDO = baseMapper.selectByIds(updateSettlementAccountStatusReqVO.getIds());
        if (settlementAccountDO == null || settlementAccountDO.isEmpty()){
            return CommonResult.error(SystemErrorCodeConstants.BASE_ACCOUNT_IS_NULL);
        }
        settlementAccountDO.forEach(settlementAccountDO1 -> {
            settlementAccountDO1.setUpdateBy(SecurityUtils.getUserId());
            settlementAccountDO1.setUpdateByName(SecurityUtils.getUsername());
            settlementAccountDO1.setUpdateTime(LocalDateTime.now());
            settlementAccountDO1.setAccountStatus(updateSettlementAccountStatusReqVO.getAccountStatus());
        });
        updateBatchById(settlementAccountDO);
        return CommonResult.success();
    }

    /**
     * 设置账户默认
     *
     * @param updateSettlementAccountStatusReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> settingAccountDefault(UpdateSettlementAccountStatusReqVO updateSettlementAccountStatusReqVO) {
        if (updateSettlementAccountStatusReqVO.getIsDefault() != null && updateSettlementAccountStatusReqVO.getIsDefault() == 0){
            List<SettlementAccountDO> isDefault  = baseMapper.getSettlementAccountDefault();
            if (isDefault != null && !isDefault.isEmpty()){
                return CommonResult.error(SystemErrorCodeConstants.BASE_ACCOUNT_IS_REPEAT);
            }
        }
        SettlementAccountDO settlementAccountDO = baseMapper.selectById(updateSettlementAccountStatusReqVO.getId());
        if (settlementAccountDO == null){
            return CommonResult.error(SystemErrorCodeConstants.BASE_ACCOUNT_IS_NULL);
        }
        settlementAccountDO.setUpdateBy(SecurityUtils.getUserId());
        settlementAccountDO.setUpdateByName(SecurityUtils.getUsername());
        settlementAccountDO.setUpdateTime(LocalDateTime.now());
        settlementAccountDO.setIsDefault(updateSettlementAccountStatusReqVO.getIsDefault());
        baseMapper.updateById(settlementAccountDO);
        return CommonResult.success();
    }

    /**
     * 账户列表
     *
     * @param querySettlementAccountReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<SettlementAccountDetailsResVO>> querySettlementAccountPage(QuerySettlementAccountReqVO querySettlementAccountReqVO) {
        IPage<SettlementAccountDetailsResVO> page = new Page<>();
        page.setCurrent(querySettlementAccountReqVO.getPage());
        page.setSize(querySettlementAccountReqVO.getSize());
        IPage<SettlementAccountDetailsResVO> result = baseMapper.querySettlementAccountPage(page, querySettlementAccountReqVO);
        PageUtil<SettlementAccountDetailsResVO> pageUtil = new PageUtil<>();
        pageUtil.setTotal(result.getTotal());
        pageUtil.setPageList(result.getRecords());
        return CommonResult.success(pageUtil);
    }
}
