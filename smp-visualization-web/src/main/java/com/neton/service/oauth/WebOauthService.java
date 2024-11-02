package com.neton.service.oauth;

import com.neton.common.CommonResult;
import com.neton.req.QueryAccAccountVO;

import java.util.Map;

public interface WebOauthService {

    public CommonResult login(QueryAccAccountVO params);
}
