package com.cross.whale.service.system.impl;

import com.cross.whale.common.CommonResult;
import com.cross.whale.feign.system.SystemServiceClient;
import com.cross.whale.req.CreateSystemMenuVO;
import com.cross.whale.req.QuerySystemMenuVO;
import com.cross.whale.req.UpdateSystemMenuVO;
import com.cross.whale.res.SystemMenuDetailsVO;
import com.cross.whale.service.system.WebSystemMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: TheSunshine
 * @create: 2024-11-12 16:00
 **/
@Service
@Slf4j
public class WebSystemMenuServiceImpl implements WebSystemMenuService {

    @Autowired
    private SystemServiceClient systemServiceClient;

    /**
     * 创建菜单
     *
     * @param createSystemMenuVO
     * @return
     */
    @Override
    public CommonResult createSystemMenu(CreateSystemMenuVO createSystemMenuVO) {
        return systemServiceClient.createSystemMenu(createSystemMenuVO);
    }

    /**
     * 更新菜单
     *
     * @param updateSystemMenuVO
     * @return
     */
    @Override
    public CommonResult updateSystemMenu(UpdateSystemMenuVO updateSystemMenuVO) {
        return systemServiceClient.updateSystemMenu(updateSystemMenuVO);
    }

    /**
     * 菜单详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<SystemMenuDetailsVO> getSystemMenuDetail(Long id) {
        return systemServiceClient.getSystemMenuDetail(id);
    }

    /**
     * 菜单列表
     *
     * @param querySystemMenuVO
     * @return
     */
    @Override
    public CommonResult<List<SystemMenuDetailsVO>> getSystemMenuList(QuerySystemMenuVO querySystemMenuVO) {
        return systemServiceClient.getSystemMenuList(querySystemMenuVO);
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult deletedSystemMenuInfo(Long id) {
        return systemServiceClient.deletedSystemMenuInfo(id);
    }
}
