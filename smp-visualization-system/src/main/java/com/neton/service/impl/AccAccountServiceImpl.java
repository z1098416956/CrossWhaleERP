package com.neton.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.common.SystemErrorCodeConstants;
import com.neton.dao.AccountDao;
import com.neton.entity.AccAccountDO;
import com.neton.req.CreateAccAccountVO;
import com.neton.req.QueryAccAccountVO;
import com.neton.req.UpdateAccAccountVO;
import com.neton.res.AccAccountVO;
import com.neton.res.UserInfoVO;
import com.neton.service.AccAccountService;
import com.neton.utils.BCryptUtils;
import com.neton.utils.SecurityUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        userInfoVO.setAccountNo(accAccountDO.getAccountNo());
        return CommonResult.success(userInfoVO);
    }

    @Override
    public CommonResult<PageUtil<UserInfoVO>> queryUserInfoPage(QueryAccAccountVO queryAccAccountVO) {
        IPage<UserInfoVO> page = new Page<>();
        page.setCurrent(queryAccAccountVO.getPage());
        page.setSize(queryAccAccountVO.getSize());
        IPage<UserInfoVO> iPage = accountDao.queryUserInfoPage(page, queryAccAccountVO);
        PageUtil<UserInfoVO> pageUtil = new PageUtil<>();
        pageUtil.setPageList(iPage.getRecords());
        pageUtil.setTotal(iPage.getTotal());
        return CommonResult.success(pageUtil);
    }

    /**
     * 创建用户
     *
     * @param createAccAccountVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult createUserInfo(CreateAccAccountVO createAccAccountVO) {
        if (StringUtils.isBlank(createAccAccountVO.getAccountNo())){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ACCOUNT_NO_ERR);
        }
        if (StringUtils.isBlank(createAccAccountVO.getUsername())){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_USERNAME_ERR);
        }
        List<AccAccountDO> accAccountDOS = accountDao.queryAccountInfoByParams(createAccAccountVO.getAccountNo(), null);
        if (!accAccountDOS.isEmpty()){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_USERNAME_ERR);
        }
        List<AccAccountDO> accAccountDOS1 = accountDao.queryAccountInfoByParams(null, createAccAccountVO.getUsername());
        if (!accAccountDOS1.isEmpty()){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_USERNAME_REPEAT);
        }
        AccAccountDO accAccountDO = new AccAccountDO();
        accAccountDO.setAccountNo(createAccAccountVO.getAccountNo());
        accAccountDO.setAccountName(createAccAccountVO.getUsername());
        accAccountDO.setAvatar(createAccAccountVO.getAvatar());
        accAccountDO.setCreateByName(SecurityUtils.getUsername());
        accAccountDO.setCreateTime(LocalDateTime.now());
        accAccountDO.setCreateBy(SecurityUtils.getUserId());
        accAccountDO.setEnabled(true);
        accAccountDO.setIsDeleted(0);
        accAccountDO.setAccountPassword(BCryptUtils.getPWDStr("123456"));
        accountDao.insert(accAccountDO);
        return CommonResult.success();
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult deleteUserById(Long id) {
        if (id == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_USER_ID_IS_NULL);
        }
        int i = accountDao.deleteById(id);
        return i == 0 ? CommonResult.error(SystemErrorCodeConstants.SYSTEM_USER_ID_IS_ERR) : CommonResult.success();
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<UserInfoVO> getUserInfoById(Long id) {
        AccAccountDO accAccountDO = accountDao.selectById(id);
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setAvatar(accAccountDO.getAvatar());
        userInfoVO.setName(accAccountDO.getAccountName());
        userInfoVO.setId(accAccountDO.getId());
        userInfoVO.setAccountNo(accAccountDO.getAccountNo());
        return CommonResult.success(userInfoVO);
    }

    /**
     * 更新用户信息
     *
     * @param updateAccAccountVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult updateUserInfo(UpdateAccAccountVO updateAccAccountVO) {
        if (StringUtils.isBlank(updateAccAccountVO.getAccountNo())){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_ACCOUNT_NO_ERR);
        }
        if (updateAccAccountVO.getId() == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_USER_ID_IS_NULL);
        }
        if (StringUtils.isBlank(updateAccAccountVO.getName())){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_USERNAME_ERR);
        }
        AccAccountDO accAccountDO = accountDao.selectById(updateAccAccountVO.getId());
        if (accAccountDO == null){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_USER_ID_IS_ERR);
        }
        BeanUtils.copyProperties(updateAccAccountVO,accAccountDO);
        accAccountDO.setAccountName(updateAccAccountVO.getName());
        accAccountDO.setUpdateBy(SecurityUtils.getUserId());
        accAccountDO.setUpdateByName(SecurityUtils.getUsername());
        accAccountDO.setUpdateTime(LocalDateTime.now());
        accountDao.updateById(accAccountDO);
        return CommonResult.success();
    }
}
