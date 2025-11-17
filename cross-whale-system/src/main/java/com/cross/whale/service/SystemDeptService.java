package com.cross.whale.service;

import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.req.CreateSystemDeptVO;
import com.cross.whale.req.QuerySystemDeptVO;
import com.cross.whale.req.UpdateSystemDeptVO;
import com.cross.whale.res.SystemDeptDetailsVO;
import com.cross.whale.res.SystemDeptTree;

import java.util.List;

public interface SystemDeptService {

    /**
     * 创建系统部门
     * @param createSystemDeptVO
     * @return
     */
    public CommonResult createSystemDeptInfo(CreateSystemDeptVO createSystemDeptVO);

    /**
     * 更新系统部门
     * @param updateSystemDeptVO
     * @return
     */
    public CommonResult updateSystemDeptInfo(UpdateSystemDeptVO updateSystemDeptVO);

    /**
     * 系统部门详情
     * @param id
     * @return
     */
    public CommonResult<SystemDeptDetailsVO> getSystemDeptDetailsInfo(Long id);

    /**
     * 根据id获取当前这个id的tree
     * @param id
     * @return
     */
    public CommonResult<List<SystemDeptTree>> getSystemDeptTreeById(Long id);

    /**
     * 查询部门信息分页
     * @param querySystemDeptVO
     * @return
     */
    public CommonResult<PageUtil<SystemDeptDetailsVO>> getSystemDeptPage(QuerySystemDeptVO querySystemDeptVO);

    /**
     * 删除部门信息
     * @param id
     * @return
     */
    public CommonResult deleteSystemDeptInfo(Long id);
}
