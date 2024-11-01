package com.neton.service.oauth;

import com.neton.common.CommonResult;

import java.util.Map;

public interface WebOauthService {

    public CommonResult login(Map<String,String> params);
}
