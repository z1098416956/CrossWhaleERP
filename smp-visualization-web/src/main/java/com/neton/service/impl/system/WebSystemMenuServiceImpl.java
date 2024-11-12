package com.neton.service.impl.system;

import com.neton.common.CommonResult;
import com.neton.feign.system.SystemServiceClient;
import com.neton.req.CreateSystemMenuVO;
import com.neton.req.QuerySystemMenuVO;
import com.neton.req.UpdateSystemMenuVO;
import com.neton.res.SystemMenuDetailsVO;
import com.neton.service.system.WebSystemMenuService;
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
