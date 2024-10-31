package com.neton.service.system;

import com.neton.commonality.common.CommonResult;
import com.neton.commonality.req.QueryAccAccountVO;
import com.neton.commonality.res.AccAccountVO;

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
}
