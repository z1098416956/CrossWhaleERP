package com.neton.service.impl;

import com.neton.common.CommonResult;
import com.neton.dao.SystemRoleDao;
import com.neton.dao.SystemUserRoleDao;
import com.neton.entity.SystemUserRoleDO;
import com.neton.req.QueryRoleVO;
import com.neton.res.SystemRuleVO;
import com.neton.service.SystemUserRoleService;
import com.neton.utils.SecurityUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
