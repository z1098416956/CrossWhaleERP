package com.neton.service.system;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.req.CreateSystemDeptVO;
import com.neton.req.QuerySystemDeptVO;
import com.neton.req.UpdateSystemDeptVO;
import com.neton.res.SystemDeptDetailsVO;
import com.neton.res.SystemDeptTree;

import java.util.List;

/**
 * @author: TheSunshine
 * @create: 2024-11-12 14:05
 **/
public interface WebSystemDeptService {

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
