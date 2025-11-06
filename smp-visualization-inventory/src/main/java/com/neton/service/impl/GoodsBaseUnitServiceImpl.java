package com.neton.service.impl;

import cn.hutool.db.PageResult;
import com.neton.bean.NetonBeanUtils;
import com.neton.common.CommonResult;
import com.neton.common.SystemErrorCodeConstants;
import com.neton.dao.GoodsBaseUnitDao;
import com.neton.dao.GoodsBaseUnitExtendDao;
import com.neton.entity.GoodsBaseUnitDO;
import com.neton.entity.GoodsBaseUnitExtendDO;
import com.neton.req.CreateUnitExtendVO;
import com.neton.req.CreateUnitVO;
import com.neton.req.QueryUnitReqVO;
import com.neton.req.UpdateUnitReqVO;
import com.neton.res.UnitResVO;
import com.neton.service.GoodsBaseUnitService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsBaseUnitServiceImpl implements GoodsBaseUnitService {

    @Resource
    private GoodsBaseUnitDao goodsBaseUnitDao;

    @Resource
    private GoodsBaseUnitExtendDao goodsBaseUnitExtendDao;
    /**
     * 创建基本单位副单位
     *
     * @param createUnitVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> createGoodsBaseUnitInfo(CreateUnitVO createUnitVO) {

        if (StringUtils.isBlank(createUnitVO.getUnitName())) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_UNIT_NAME_IS_NULL);
        }
        GoodsBaseUnitDO goodsBaseUnitDO = new GoodsBaseUnitDO();
        BeanUtils.copyProperties(createUnitVO, goodsBaseUnitDO);
        goodsBaseUnitDao.insert(goodsBaseUnitDO);
        if (createUnitVO.getExtendList() == null || createUnitVO.getExtendList().isEmpty()){
            return CommonResult.success();
        }
        List<CreateUnitExtendVO> extendList = createUnitVO.getExtendList();
        List<GoodsBaseUnitExtendDO> unitExtendVOList = NetonBeanUtils.toBean(extendList, GoodsBaseUnitExtendDO.class);
        unitExtendVOList.forEach(unitExtendDO -> {
            unitExtendDO.setUnitId(goodsBaseUnitDO.getId());
        });
        goodsBaseUnitExtendDao.insert(unitExtendVOList);
        return CommonResult.success();
    }

    /**
     * 分页查询基本单位副单位
     *
     * @param queryUnitReqVO
     * @return
     */
    @Override
    public CommonResult<PageResult<UnitResVO>> getGoodsBaseUnitPage(QueryUnitReqVO queryUnitReqVO) {
        return null;
    }

    /**
     * 根据ID查询基本单位副单位
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<UnitResVO> getGoodsBaseUnitById(Long id) {
        return null;
    }

    /**
     * 更新基本单位副单位
     *
     * @param updateUnitVO
     * @return
     */
    @Override
    public CommonResult<Void> updateGoodsBaseUnitInfo(UpdateUnitReqVO updateUnitVO) {
        return null;
    }

    /**
     * 删除基本单位副单位
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<Void> deleteGoodsBaseUnitInfo(Long id) {
        return null;
    }
}
