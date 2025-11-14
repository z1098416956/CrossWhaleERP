package com.neton.service.impl;

import com.neton.common.CommonResult;
import com.neton.common.SystemErrorCodeConstants;
import com.neton.dao.SystemRoleDao;
import com.neton.dao.SystemUserRoleDao;
import com.neton.entity.SystemUserRoleDO;
import com.neton.req.CreateUserRoleVO;
import com.neton.req.QueryRoleVO;
import com.neton.res.SystemRuleVO;
import com.neton.service.SystemUserRoleService;
import com.neton.utils.GeneratedBarcodeUtils;
import com.neton.utils.SecurityUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: TheSunshine
 * @create: 2024-11-11 18:11
 **/
@Service
@Slf4j
public class SystemUserRoleServiceImpl implements SystemUserRoleService {

    @Resource
    private SystemUserRoleDao systemUserRoleDao;

    @Resource
    private SystemRoleDao systemRoleDao;

    @Autowired
    private GeneratedBarcodeUtils generatedBarcodeUtils;

    /**
     * 获取用户角色
     *
     * @return
     */
    @Override
    public CommonResult<List<SystemRuleVO>> getUserRoleInfo(Long id) {
        List<SystemRuleVO> userRoleInfo = systemUserRoleDao.getUserRoleInfo(id);
       /* List<SystemRuleVO> systemRoleList = systemRoleDao.getSystemRoleList(new QueryRoleVO());
        if (!userRoleInfo.isEmpty()){
            for (SystemRuleVO vo : systemRoleList){
                boolean exists = userRoleInfo.stream()
                        .anyMatch(x -> x.getRoleId().compareTo(vo.getId()) == 0);
                if (exists){
                    vo.setDisabled(true);
                }
            }
        }*/
        return CommonResult.success(userRoleInfo);
    }

    /**
     * 创建用户权限
     *
     * @param createUserRoleVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult createUserRoleInfo(CreateUserRoleVO createUserRoleVO) {
        if (createUserRoleVO.getUserId() == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_USER_ID_IS_NULL);
        }
        if (createUserRoleVO.getRoleList() == null || createUserRoleVO.getRoleList().isEmpty()){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_USER_ROLE_IS_NULL);
        }
        systemUserRoleDao.deleteByUserId(createUserRoleVO.getUserId());
        List<SystemUserRoleDO> list = new ArrayList<>();
        for (Long roleId : createUserRoleVO.getRoleList()){
            SystemUserRoleDO systemUserRoleDO = new SystemUserRoleDO();
            systemUserRoleDO.setRoleId(roleId);
            systemUserRoleDO.setUserId(createUserRoleVO.getUserId());
            systemUserRoleDO.setCreateBy(SecurityUtils.getUserId());
            systemUserRoleDO.setCreateByName(SecurityUtils.getUsername());
            systemUserRoleDO.setIsDeleted(0);
            systemUserRoleDO.setCreateTime(LocalDateTime.now());
            list.add(systemUserRoleDO);
        }
        systemUserRoleDao.insert(list);
        return CommonResult.success();
    }

    /**
     * 获取条码
     *
     * @param moduleName
     * @return
     */
    @Override
    public CommonResult<String> getBarcode(String moduleName) {
        String barcode = generatedBarcodeUtils.generateReceipts(moduleName);
        return CommonResult.success(barcode);
    }

    /**
     * 获取单据号
     *
     * @param moduleName
     * @return
     */
    @Override
    public CommonResult<String> getReceipts(String moduleName) {
        String barcode = generatedBarcodeUtils.generateReceipts(moduleName);
        return CommonResult.success(barcode);
    }
}
