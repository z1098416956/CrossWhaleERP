package com.cross.whale.service.system;


import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateAccAccountVO;
import com.cross.whale.req.QueryAccAccountVO;
import com.cross.whale.req.UpdateAccAccountVO;
import com.cross.whale.res.AccAccountVO;
import com.cross.whale.res.UserInfoVO;

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

    public CommonResult deleteUserById(Long id);

    public CommonResult<UserInfoVO> getUserInfoById(Long id);

    /**
     * 更新用户信息
     * @param updateAccAccountVO
     * @return
     */
    public CommonResult updateUserInfo(UpdateAccAccountVO updateAccAccountVO);
}
