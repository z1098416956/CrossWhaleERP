package com.neton.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.dao.AccountDao;
import com.neton.entity.AccAccountDO;
import com.neton.req.QueryAccAccountVO;
import com.neton.res.AccAccountVO;
import com.neton.res.UserInfoVO;
import com.neton.service.AccAccountService;
import com.neton.utils.SecurityUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TheSunshine
 * @date 2024-10-31 14:54:00
 */
@Service
@Slf4j
public class AccAccountServiceImpl implements AccAccountService {

    @Resource
    private AccountDao accountDao;
    @Override
    public CommonResult<List<AccAccountVO>> queryAccountInfoList(QueryAccAccountVO queryAccAccountVO) {
        List<AccAccountVO> accAccountVOS = accountDao.queryAccountInfoList(queryAccAccountVO);
        return CommonResult.success(accAccountVOS);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @Override
    public CommonResult<UserInfoVO> getUserInfo() {
        AccAccountDO accAccountDO = accountDao.selectById(SecurityUtils.getUserId());
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setAvatar(accAccountDO.getAvatar());
        userInfoVO.setName(accAccountDO.getAccountName());
        userInfoVO.setId(accAccountDO.getId());
        List<String> str = new ArrayList<>();
        str.add("admin");
        userInfoVO.setRoles(str);
        userInfoVO.setName(accAccountDO.getAccountNo());
        return CommonResult.success(userInfoVO);
    }

    @Override
    public CommonResult<PageUtil<UserInfoVO>> queryUserInfoPage(QueryAccAccountVO queryAccAccountVO) {
        IPage<UserInfoVO> page = new Page<>();
        page.setCurrent(queryAccAccountVO.getPage());
        page.setSize(queryAccAccountVO.getSize());
        IPage<UserInfoVO> iPage = accountDao.queryUserInfoPage(page, queryAccAccountVO);
        PageUtil<UserInfoVO> pageUtil = new PageUtil<>();
        pageUtil.setPateList(iPage.getRecords());
        pageUtil.setTotal(iPage.getTotal());
        return CommonResult.success(pageUtil);
    }
}
