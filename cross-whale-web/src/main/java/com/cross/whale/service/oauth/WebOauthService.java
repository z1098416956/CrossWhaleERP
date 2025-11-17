package com.cross.whale.service.oauth;

import com.cross.whale.common.CommonResult;
import com.cross.whale.req.QueryAccAccountVO;

public interface WebOauthService {

    public CommonResult login(QueryAccAccountVO params);

    public CommonResult logout();
}
