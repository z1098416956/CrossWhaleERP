package com.neton.service.impl.oauth;

import com.neton.common.CommonResult;
import com.neton.feign.oauth.OauthServiceClient;
import com.neton.req.QueryAccAccountVO;
import com.neton.service.oauth.WebOauthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
