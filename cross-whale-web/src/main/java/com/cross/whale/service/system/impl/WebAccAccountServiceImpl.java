package com.cross.whale.service.system.impl;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.feign.system.SystemServiceClient;
import com.cross.whale.req.CreateAccAccountVO;
import com.cross.whale.req.QueryAccAccountVO;
import com.cross.whale.req.UpdateAccAccountVO;
import com.cross.whale.res.AccAccountVO;
import com.cross.whale.res.UserInfoVO;
import com.cross.whale.service.system.WebAccAccountService;
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
