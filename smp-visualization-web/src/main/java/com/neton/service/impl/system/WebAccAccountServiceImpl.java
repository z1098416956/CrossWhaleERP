package com.neton.service.impl.system;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.system.SystemServiceClient;
import com.neton.req.CreateAccAccountVO;
import com.neton.req.QueryAccAccountVO;
import com.neton.req.UpdateAccAccountVO;
import com.neton.res.AccAccountVO;
import com.neton.res.UserInfoVO;
import com.neton.service.system.WebAccAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public CommonResult<PageUtil<UserInfoVO>> queryUserInfoPage(QueryAccAccountVO queryAccAccountVO) {
        return systemServiceClient.queryUserInfoPage(queryAccAccountVO);
    }

    @Override
    public CommonResult createUserInfo(CreateAccAccountVO createAccAccountVO) {

        return systemServiceClient.createUserInfo(createAccAccountVO);
    }

    @Override
    public CommonResult deleteUserById(Long id) {
        return systemServiceClient.deleteUserById(id);
    }

    @Override
    public CommonResult<UserInfoVO> getUserInfoById(Long id) {
        return systemServiceClient.getUserInfoById(id);
    }

    /**
     * 更新用户信息
     *
     * @param updateAccAccountVO
     * @return
     */
    @Override
    public CommonResult updateUserInfo(UpdateAccAccountVO updateAccAccountVO) {
        return systemServiceClient.updateUserInfo(updateAccAccountVO);
    }
}
