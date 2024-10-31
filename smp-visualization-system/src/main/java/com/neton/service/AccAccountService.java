package com.neton.service;

import com.neton.commonality.common.CommonResult;
import com.neton.commonality.req.QueryAccAccountVO;
import com.neton.commonality.res.AccAccountVO;

import java.util.List;

/**
 * @author TheSunshine
 * @date 2024-10-31 14:53:41
 */
public interface AccAccountService {

    /**
     * 查询用户列表
     * @param queryAccAccountVO
     * @return
     */
    public CommonResult<List<AccAccountVO>> queryAccountInfoList(QueryAccAccountVO queryAccAccountVO);
}
