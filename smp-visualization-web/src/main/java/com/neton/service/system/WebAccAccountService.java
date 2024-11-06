package com.neton.service.system;


import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.CreateAccAccountVO;
import com.neton.req.QueryAccAccountVO;
import com.neton.res.AccAccountVO;
import com.neton.res.UserInfoVO;

import java.util.List;

/**
 * @author TheSunshine
 * @date 2024-10-31 15:10:58
 */
public interface WebAccAccountService {

    /**
     * 查询用户列表
     * @param queryAccAccountVO
     * @return
     */
    public CommonResult<List<AccAccountVO>> queryAccountInfoList(QueryAccAccountVO queryAccAccountVO);


    public CommonResult<UserInfoVO> getUserInfo();

    public CommonResult<PageUtil<UserInfoVO>> queryUserInfoPage(QueryAccAccountVO queryAccAccountVO);

    public CommonResult createUserInfo(CreateAccAccountVO createAccAccountVO);
}
