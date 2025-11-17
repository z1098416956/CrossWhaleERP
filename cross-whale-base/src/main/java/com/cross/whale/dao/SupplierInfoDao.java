package com.cross.whale.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cross.whale.entity.SupplierInfoDO;
import com.cross.whale.req.QuerySupplierInfoReqVO;
import com.cross.whale.res.SupplierInfoDetailsResVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierInfoDao extends BaseMapper<SupplierInfoDO> {

    /**
     * 查询供应商名称是否存在
     * @param supperName
     * @return
     */
    default List<SupplierInfoDO> checkSupperName(String supperName) {
        LambdaQueryWrapper<SupplierInfoDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SupplierInfoDO::getSupplierName, supperName);
        return selectList(wrapper);
    }

    /**
     * 查询供应商分页
     * @param page
     * @param querySupplierInfoReqVO
     * @return
     */
    IPage<SupplierInfoDetailsResVO>listSupplierInfo(IPage<SupplierInfoDetailsResVO> page,@Param("params") QuerySupplierInfoReqVO querySupplierInfoReqVO);
}
