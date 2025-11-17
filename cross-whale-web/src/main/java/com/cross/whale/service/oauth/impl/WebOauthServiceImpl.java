package com.cross.whale.service.oauth.impl;

import com.cross.whale.common.CommonResult;
import com.cross.whale.feign.oauth.OauthServiceClient;
import com.cross.whale.req.QueryAccAccountVO;
import com.cross.whale.service.oauth.WebOauthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WebOauthServiceImpl implements WebOauthService {

    @Autowired
    private OauthServiceClient oauthServiceClient;
    @Override
    public CommonResult login(QueryAccAccountVO params) {

        return oauthServiceClient.login(params);
    }

    @Override
    public CommonResult logout() {
        return oauthServiceClient.logout();
    }
}
