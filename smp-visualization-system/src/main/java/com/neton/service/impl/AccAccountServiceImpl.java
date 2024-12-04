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
import com.neton.res.SystemDeptTree;
import com.neton.res.UserInfoVO;
import com.neton.service.AccAccountService;
import com.neton.service.SystemDeptService;
import com.neton.utils.BCryptUtils;
import com.neton.utils.SecurityUtils;
import com.neton.utils.tree.TreeUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TheSunshine
 * @date 2024-10-31 14:54:00
 */
@Service
@Slf4j
public class AccAccountServiceImpl implements AccAccountService {

    @Resource
    private AccountDao accountDao;

    @Autowired
    private SystemDeptService systemDeptService;
    @Override
    public CommonResult<List<AccAccountVO>> queryAccountInfoList(QueryAccAccountVO queryAccAccountVO) {
        if (queryAccAccountVO.getDeptId() != null){
            CommonResult<List<SystemDeptTree>> tree = systemDeptService.getSystemDeptTreeById(queryAccAccountVO.getDeptId());
            if (tree.getCode() != 0){
                return CommonResult.error(tree.getCode(),tree.getMessage());
            }
            List<SystemDeptTree> data = tree.getData();
            if (data == null || data.isEmpty()){
                return CommonResult.success(new ArrayList<AccAccountVO>());
            }
            List<SystemDeptTree> trees = TreeUtils.buildTree(data, queryAccAccountVO.getDeptId());
            if (trees == null || trees.isEmpty()){
                return CommonResult.success(new ArrayList<AccAccountVO>());
            }
            List<Long> longs = TreeUtils.treeToList(trees).stream()
                    .map(SystemDeptTree::getId)
                    .collect(Collectors.toCollection(ArrayList::new));
            longs.add(queryAccAccountVO.getDeptId());
            queryAccAccountVO.setDeptIds(longs);
        }
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
        userInfoVO.setDeptId(accAccountDO.getDeptId());
        List<String> str = new ArrayList<>();
        str.add("admin");
        userInfoVO.setRoles(str);
        userInfoVO.setAccountNo(accAccountDO.getAccountNo());
        return CommonResult.success(userInfoVO);
    }

    @Override
    public CommonResult<PageUtil<UserInfoVO>> queryUserInfoPage(QueryAccAccountVO queryAccAccountVO) {
        if (queryAccAccountVO.getDeptId() != null && queryAccAccountVO.getDeptId().compareTo(0L) > 0){
            CommonResult<List<SystemDeptTree>> tree = systemDeptService.getSystemDeptTreeById(queryAccAccountVO.getDeptId());
            if (tree.getCode() != 0){
                return CommonResult.error(tree.getCode(),tree.getMessage());
            }
            List<SystemDeptTree> data = tree.getData();
            if (data == null || data.isEmpty()){
                return CommonResult.success(new PageUtil<>());
            }
            List<SystemDeptTree> trees = TreeUtils.buildTree(data, queryAccAccountVO.getDeptId());
            List<Long> deptIds = new ArrayList<>();
            if (trees != null && !trees.isEmpty()){
                List<Long> longs = TreeUtils.treeToList(trees).stream()
                        .map(SystemDeptTree::getId)
                        .collect(Collectors.toCollection(ArrayList::new));
                deptIds.addAll(longs);
            }
            deptIds.add(queryAccAccountVO.getDeptId());
            queryAccAccountVO.setDeptIds(deptIds);
        }
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
        if (StringUtils.isBlank(createAccAccountVO.getUserName())){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_USERNAME_ERR);
        }
        List<AccAccountDO> accAccountDOS = accountDao.queryAccountInfoByParams(createAccAccountVO.getAccountNo(), null);
        if (!accAccountDOS.isEmpty()){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_USERNAME_ERR);
        }
        List<AccAccountDO> accAccountDOS1 = accountDao.queryAccountInfoByParams(null, createAccAccountVO.getUserName());
        if (!accAccountDOS1.isEmpty()){
            return CommonResult.error(SystemErrorCodeConstants.SYSTEM_USERNAME_REPEAT);
        }
        AccAccountDO accAccountDO = new AccAccountDO();
        accAccountDO.setAccountNo(createAccAccountVO.getAccountNo());
        accAccountDO.setAccountName(createAccAccountVO.getUserName());
        accAccountDO.setDeptId(createAccAccountVO.getDeptId());
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
        userInfoVO.setDeptId(accAccountDO.getDeptId());
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
        accAccountDO.setDeptId(updateAccAccountVO.getDeptId());
        accAccountDO.setUpdateBy(SecurityUtils.getUserId());
        accAccountDO.setUpdateByName(SecurityUtils.getUsername());
        accAccountDO.setUpdateTime(LocalDateTime.now());
        accountDao.updateById(accAccountDO);
        return CommonResult.success();
    }
}
