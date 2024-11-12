package com.neton.service.impl.system;

import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.feign.system.SystemServiceClient;
import com.neton.req.CreateSystemDeptVO;
import com.neton.req.QuerySystemDeptVO;
import com.neton.req.UpdateSystemDeptVO;
import com.neton.res.SystemDeptDetailsVO;
import com.neton.res.SystemDeptTree;
import com.neton.service.system.WebSystemDeptService;
import com.neton.utils.tree.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: TheSunshine
 * @create: 2024-11-12 14:05
 **/
@Service
@Slf4j
public class WebSystemDeptServiceImpl implements WebSystemDeptService {

    @Autowired
    private SystemServiceClient systemServiceClient;

    /**
     * 创建系统部门
     *
     * @param createSystemDeptVO
     * @return
     */
    @Override
    public CommonResult createSystemDeptInfo(CreateSystemDeptVO createSystemDeptVO) {
        return systemServiceClient.createSystemDeptInfo(createSystemDeptVO);
    }

    /**
     * 更新系统部门
     *
     * @param updateSystemDeptVO
     * @return
     */
    @Override
    public CommonResult updateSystemDeptInfo(UpdateSystemDeptVO updateSystemDeptVO) {
        return systemServiceClient.updateSystemDeptInfo(updateSystemDeptVO);
    }

    /**
     * 系统部门详情
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<SystemDeptDetailsVO> getSystemDeptDetailsInfo(Long id) {
        return systemServiceClient.getSystemDeptDetailsInfo(id);
    }

    /**
     * 根据id获取当前这个id的tree
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<List<SystemDeptTree>> getSystemDeptTreeById(Long id) {
        CommonResult<List<SystemDeptTree>> tree = systemServiceClient.getSystemDeptTreeById(id);
        List<SystemDeptTree> list = tree.getData();
        if (list.isEmpty()){
            return CommonResult.success(list);
        }
        List<SystemDeptTree> trees = TreeUtils.buildTree(list, id);
        return CommonResult.success(trees);
    }

    /**
     * 查询部门信息分页
     *
     * @param querySystemDeptVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<SystemDeptDetailsVO>> getSystemDeptPage(QuerySystemDeptVO querySystemDeptVO) {
        return systemServiceClient.getSystemDeptPage(querySystemDeptVO);
    }

    /**
     * 删除部门信息
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult deleteSystemDeptInfo(Long id) {
        return systemServiceClient.deleteSystemDeptInfo(id);
    }
}
