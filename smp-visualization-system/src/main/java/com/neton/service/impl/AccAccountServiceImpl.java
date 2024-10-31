package com.neton.service.impl;

import com.neton.common.CommonResult;
import com.neton.dao.AccountDao;
import com.neton.req.QueryAccAccountVO;
import com.neton.res.AccAccountVO;
import com.neton.service.AccAccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
}
