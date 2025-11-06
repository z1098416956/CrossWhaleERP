package com.neton.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neton.bean.NetonBeanUtils;
import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
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
import com.neton.utils.SecurityUtils;
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
        //设置默认启用
        goodsBaseUnitDO.setIsEnabled(0);
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
    public CommonResult<PageUtil<UnitResVO>> getGoodsBaseUnitPage(QueryUnitReqVO queryUnitReqVO) {
        IPage<UnitResVO> page = new Page<>();
        page.setCurrent(queryUnitReqVO.getPage());
        page.setSize(queryUnitReqVO.getSize());
        IPage<UnitResVO> iPage = goodsBaseUnitDao.getGoodsBaseUnitPage(page, queryUnitReqVO);
        PageUtil<UnitResVO> pageResult = new PageUtil<>();
        pageResult.setPageList(iPage.getRecords());
        pageResult.setTotal(iPage.getTotal());
        return CommonResult.success(pageResult);
    }

    /**
     * 根据ID查询基本单位副单位
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<UnitResVO> getGoodsBaseUnitById(Long id) {
        UnitResVO unitById = goodsBaseUnitDao.getUnitById(id);
        return CommonResult.success(unitById);
    }

    /**
     * 更新基本单位副单位
     *
     * @param updateUnitVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> updateGoodsBaseUnitInfo(UpdateUnitReqVO updateUnitVO) {
        if (updateUnitVO.getId() == null) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_UNIT_ID_IS_NULL);
        }
        GoodsBaseUnitDO goodsBaseUnitDO = goodsBaseUnitDao.selectById(updateUnitVO.getId());
        if (goodsBaseUnitDO == null) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_UNIT_ID_IS_ERR);
        }
        NetonBeanUtils.copyProperties(updateUnitVO, goodsBaseUnitDO);
        goodsBaseUnitDao.updateById(goodsBaseUnitDO);
        if (updateUnitVO.getExtendList() == null || updateUnitVO.getExtendList().isEmpty()) {
            return CommonResult.success();
        }
        goodsBaseUnitExtendDao.deleteByGoodsBaseUnitId(updateUnitVO.getId());
        List<CreateUnitExtendVO> extendList = updateUnitVO.getExtendList();
        List<GoodsBaseUnitExtendDO> unitExtendVOList = NetonBeanUtils.toBean(extendList, GoodsBaseUnitExtendDO.class);
        unitExtendVOList.forEach(unitExtendDO -> {
            unitExtendDO.setUnitId(goodsBaseUnitDO.getId());
        });
        goodsBaseUnitExtendDao.insert(unitExtendVOList);
        return CommonResult.success();
    }

    /**
     * 删除基本单位副单位
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> deleteGoodsBaseUnitInfo(Long id) {
        GoodsBaseUnitDO goodsBaseUnitDO = goodsBaseUnitDao.selectById(id);
        if (goodsBaseUnitDO == null) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_UNIT_ID_IS_ERR);
        }
        goodsBaseUnitDao.deleteById(id);
        goodsBaseUnitExtendDao.updateByGoodsBaseUnitId(id, SecurityUtils.getUserId());
        return CommonResult.success();
    }
}
