package com.cross.whale.service;


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
 * @date 2024-10-31 14:53:41
 */
public interface AccAccountService {

    /**
     * 查询用户列表
     * @param queryAccAccountVO
     * @return
     */
    public CommonResult<List<AccAccountVO>> queryAccountInfoList(QueryAccAccountVO queryAccAccountVO);


    /**
     * 获取用户信息
     * @return
     */
    public CommonResult<UserInfoVO> getUserInfo();

    public CommonResult<PageUtil<UserInfoVO>> queryUserInfoPage(QueryAccAccountVO queryAccAccountVO);

    /**
     * 创建用户
     * @param createAccAccountVO
     * @return
     */
    public CommonResult createUserInfo(CreateAccAccountVO createAccAccountVO);

    /**
     * 删除用户
     * @param id
     * @return
     */
    public CommonResult deleteUserById(Long id);

    /**
     * 获取用户信息
     * @return
     */
    public CommonResult<UserInfoVO> getUserInfoById(Long id);

    /**
     * 更新用户信息
     * @param updateAccAccountVO
     * @return
     */
    public CommonResult updateUserInfo(UpdateAccAccountVO updateAccAccountVO);
}
