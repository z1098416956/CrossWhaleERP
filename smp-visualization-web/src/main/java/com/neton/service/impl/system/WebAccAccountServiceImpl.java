package com.neton.service.impl.system;

import com.neton.common.CommonResult;
import com.neton.feign.system.SystemServiceClient;
import com.neton.req.QueryAccAccountVO;
import com.neton.res.AccAccountVO;
import com.neton.res.UserInfoVO;
import com.neton.service.system.WebAccAccountService;
import com.neton.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author TheSunshine
 * @date 2024-10-31 15:11:53
 */
@Service
@Slf4j
public class WebAccAccountServiceImpl implements WebAccAccountService {

    @Autowired
    private SystemServiceClient systemServiceClient;

    /**
     * 查询用户列表
     *
     * @param queryAccAccountVO
     * @return
     */
    @Override
    public CommonResult<List<AccAccountVO>> queryAccountInfoList(QueryAccAccountVO queryAccAccountVO) {
        return systemServiceClient.queryAccountInfoList(queryAccAccountVO);
    }

    @Override
    public CommonResult<UserInfoVO> getUserInfo() {
        CommonResult<UserInfoVO> userInfo = systemServiceClient.getUserInfo();
        return userInfo;
    }
}
