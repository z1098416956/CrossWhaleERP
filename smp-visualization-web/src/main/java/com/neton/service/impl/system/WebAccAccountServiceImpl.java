package com.neton.service.impl.system;

import com.neton.common.CommonResult;
import com.neton.feign.system.SystemServiceClient;
import com.neton.req.QueryAccAccountVO;
import com.neton.res.AccAccountVO;
import com.neton.res.UserInfoVO;
import com.neton.service.system.WebAccAccountService;
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
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setIntroduction("admin");
        userInfoVO.setName("admin");
        userInfoVO.setAvatar("https://gips0.baidu.com/it/u=567323913,331130417&fm=3028&app=3028&f=JPEG&fmt=auto&q=100&size=f1000_1000");
        List<String> admin = Arrays.asList("admin");
        userInfoVO.setRoles(admin);
        return CommonResult.success(userInfoVO);
    }
}
