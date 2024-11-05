package com.neton.service.impl;

import com.neton.common.CommonResult;
import com.neton.dao.AccountDao;
import com.neton.entity.AccAccountDO;
import com.neton.req.QueryAccAccountVO;
import com.neton.res.AccAccountVO;
import com.neton.res.UserInfoVO;
import com.neton.service.AccAccountService;
import com.neton.utils.SecurityUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TheSunshine
 * @date 2024-10-31 14:54:00
 */
@Service
@Slf4j
public class AccAccountServiceImpl implements AccAccountService {

    @Resource
    private AccountDao accountDao;
    @Override
    public CommonResult<List<AccAccountVO>> queryAccountInfoList(QueryAccAccountVO queryAccAccountVO) {
        List<AccAccountVO> accAccountVOS = accountDao.queryAccountInfoList(queryAccAccountVO);
        return CommonResult.success(accAccountVOS);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @Override
    public CommonResult<UserInfoVO> getUserInfo() {
        AccAccountDO accAccountDO = accountDao.selectById(SecurityUtils.getUserId());
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setAvatar(accAccountDO.getAvatar());
        userInfoVO.setName(accAccountDO.getAccountName());
        userInfoVO.setId(accAccountDO.getId());
        userInfoVO.setName(accAccountDO.getAccountNo());
        return CommonResult.success(userInfoVO);
    }
}
