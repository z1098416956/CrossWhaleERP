package com.cross.whale.service;

import com.cross.whale.common.CommonResult;
import com.cross.whale.req.CreateSystemMenuVO;
import com.cross.whale.req.QuerySystemMenuVO;
import com.cross.whale.req.UpdateSystemMenuVO;
import com.cross.whale.res.SystemMenuDetailsVO;

import java.util.List;

public interface SystemMenuService {

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
