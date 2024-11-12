package com.neton.service.system;

import com.neton.common.CommonResult;
import com.neton.req.CreateSystemMenuVO;
import com.neton.req.QuerySystemMenuVO;
import com.neton.req.UpdateSystemMenuVO;
import com.neton.res.SystemMenuDetailsVO;

import java.util.List;

/**
 * @author: TheSunshine
 * @create: 2024-11-12 15:59
 **/
public interface WebSystemMenuService {

    /**
     * 创建菜单
     * @param createSystemMenuVO
     * @return
     */
    public CommonResult createSystemMenu(CreateSystemMenuVO createSystemMenuVO);

    /**
     * 更新菜单
     * @param updateSystemMenuVO
     * @return
     */
    public CommonResult updateSystemMenu(UpdateSystemMenuVO updateSystemMenuVO);


    /**
     * 菜单详情
     * @param id
     * @return
     */
    public CommonResult<SystemMenuDetailsVO> getSystemMenuDetail(Long id);

    /**
     * 菜单列表
     * @param querySystemMenuVO
     * @return
     */
    public CommonResult<List<SystemMenuDetailsVO>> getSystemMenuList(QuerySystemMenuVO querySystemMenuVO);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    public CommonResult deletedSystemMenuInfo(Long id);
}
