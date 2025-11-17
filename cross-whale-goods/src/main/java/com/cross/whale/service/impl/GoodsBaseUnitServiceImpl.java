package com.cross.whale.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cross.whale.bean.NetonBeanUtils;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.common.SystemErrorCodeConstants;
import com.cross.whale.dao.GoodsBaseUnitDao;
import com.cross.whale.dao.GoodsBaseUnitExtendDao;
import com.cross.whale.entity.GoodsBaseUnitDO;
import com.cross.whale.entity.GoodsBaseUnitExtendDO;
import com.cross.whale.req.*;
import com.cross.whale.res.UnitExtendResVO;
import com.cross.whale.res.UnitPageResVO;
import com.cross.whale.res.UnitResVO;
import com.cross.whale.service.GoodsBaseUnitService;
import com.cross.whale.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        goodsBaseUnitDO.setCreateBy(SecurityUtils.getUserId());
        goodsBaseUnitDO.setCreateTime(LocalDateTime.now());
        goodsBaseUnitDO.setCreateByName(SecurityUtils.getUsername());
        goodsBaseUnitDO.setIsDeleted(0);
        goodsBaseUnitDao.insert(goodsBaseUnitDO);
        if (createUnitVO.getExtendList() == null || createUnitVO.getExtendList().isEmpty()){
            return CommonResult.success();
        }
        List<CreateUnitExtendVO> extendList = createUnitVO.getExtendList();
        List<GoodsBaseUnitExtendDO> unitExtendVOList = NetonBeanUtils.toBean(extendList, GoodsBaseUnitExtendDO.class);
        unitExtendVOList.forEach(unitExtendDO -> {
            unitExtendDO.setUnitId(goodsBaseUnitDO.getId());
            unitExtendDO.setUpdateBy(SecurityUtils.getUserId());
            unitExtendDO.setUpdateTime(LocalDateTime.now());
            unitExtendDO.setUpdateByName(SecurityUtils.getUsername());
            unitExtendDO.setCreateByName(SecurityUtils.getUsername());
            unitExtendDO.setCreateTime(LocalDateTime.now());
            unitExtendDO.setCreateBy(SecurityUtils.getUserId());
            unitExtendDO.setIsDeleted(0);
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
        List<UnitResVO> records = iPage.getRecords();
        if (records != null && !records.isEmpty()) {
            List<Long> collect = records.stream().map(UnitResVO::getId).toList();
            List<UnitExtendResVO> list = goodsBaseUnitExtendDao.selectByGoodsBaseUnitIds(collect);
            if (list != null && !list.isEmpty()) {
                Map<Long, List<UnitExtendResVO>> map = list.stream().collect(Collectors.groupingBy(UnitExtendResVO::getUnitId));
                records.forEach(record -> {
                    record.setExtendList(map.get(record.getId()));
                });
            }
        }
        pageResult.setPageList(records);
        pageResult.setTotal(iPage.getTotal());
        return CommonResult.success(pageResult);
    }

    /**
     * 分页查询基本单位副单位
     *
     * @param queryUnitReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<UnitPageResVO>> queryGoodsBaseUnitPage(QueryUnitReqVO queryUnitReqVO) {
        IPage<UnitResVO> page = new Page<>();
        page.setCurrent(queryUnitReqVO.getPage());
        page.setSize(queryUnitReqVO.getSize());
        IPage<UnitResVO> iPage = goodsBaseUnitDao.getGoodsBaseUnitPage(page, queryUnitReqVO);
        PageUtil<UnitPageResVO> pageResult = new PageUtil<>();
        List<UnitResVO> records = iPage.getRecords();
        if (records != null && !records.isEmpty()) {
            List<Long> collect = records.stream().map(UnitResVO::getId).toList();
            List<UnitExtendResVO> list = goodsBaseUnitExtendDao.selectByGoodsBaseUnitIds(collect);
            List<UnitPageResVO> res = new ArrayList<>();
            if (list != null && !list.isEmpty()) {
                Map<Long, List<UnitExtendResVO>> map = list.stream().collect(Collectors.groupingBy(UnitExtendResVO::getUnitId));
                records.forEach(record -> {
                    List<UnitExtendResVO> resVOS = map.get(record.getId());
                    UnitPageResVO unitPageResVO = new UnitPageResVO();
                    unitPageResVO.setId(record.getId());
                    unitPageResVO.setBaseUnitName(record.getUnitName());
                    unitPageResVO.setIsEnabled(record.getIsEnabled());
                    StringBuffer sb = new StringBuffer();
                    sb.append(record.getUnitName()).append("/(");
                    if (resVOS != null && !resVOS.isEmpty()) {
                        sb.append(resVOS.get(0).getUnitExtendName());
                        sb.append("=");
                        sb.append(resVOS.get(0).getConversionRatio());
                        sb.append(record.getUnitName()+")");
                        unitPageResVO.setDeputyUnitName(resVOS.get(0).getUnitExtendName()+"="+resVOS.get(0).getConversionRatio()+record.getUnitName());
                        if (resVOS.size() >=2){
                            sb.append("/(");
                            sb.append(resVOS.get(1).getUnitExtendName());
                            sb.append("=");
                            sb.append(resVOS.get(1).getConversionRatio());
                            sb.append(record.getUnitName()+")");
                            unitPageResVO.setDeputyUnitName2(resVOS.get(1).getUnitExtendName()+"="+resVOS.get(1).getConversionRatio()+record.getUnitName());
                        }
                        if (resVOS.size() >=3){
                            sb.append("/(");
                            sb.append(resVOS.get(2).getUnitExtendName());
                            sb.append("=");
                            sb.append(resVOS.get(2).getConversionRatio());
                            sb.append(record.getUnitName()+")");
                            unitPageResVO.setDeputyUnitName3(resVOS.get(2).getUnitExtendName()+"="+resVOS.get(2).getConversionRatio()+record.getUnitName());
                        }
                    }
                    unitPageResVO.setUnitName(sb.toString());
                    res.add(unitPageResVO);
                });
            }
            pageResult.setTotal(iPage.getTotal());
            pageResult.setPageList(res);
        }
        return CommonResult.success(pageResult);
    }

    /**
     * 分页查询基本单位副单位
     *
     * @param queryUnitReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<UnitPageResVO>> queryGoodsBaseUnitPage2(QueryUnitReqVO queryUnitReqVO) {
        IPage<UnitResVO> page = new Page<>();
        page.setCurrent(queryUnitReqVO.getPage());
        page.setSize(queryUnitReqVO.getSize());
        IPage<UnitResVO> iPage = goodsBaseUnitDao.getGoodsBaseUnitPage(page, queryUnitReqVO);
        PageUtil<UnitPageResVO> pageResult = new PageUtil<>();
        List<UnitResVO> records = iPage.getRecords();
        if (records != null && !records.isEmpty()) {
            List<Long> collect = records.stream().map(UnitResVO::getId).toList();
            List<UnitExtendResVO> list = goodsBaseUnitExtendDao.selectByGoodsBaseUnitIds(collect);
            List<UnitPageResVO> res = new ArrayList<>();
            if (list != null && !list.isEmpty()) {
                Map<Long, List<UnitExtendResVO>> map = list.stream().collect(Collectors.groupingBy(UnitExtendResVO::getUnitId));
                records.forEach(record -> {
                    List<UnitExtendResVO> resVOS = map.get(record.getId());
                    UnitPageResVO unitPageResVO = new UnitPageResVO();
                    unitPageResVO.setId(record.getId());
                    unitPageResVO.setBaseUnitName(record.getUnitName());
                    unitPageResVO.setIsEnabled(record.getIsEnabled());
                    StringBuffer sb = new StringBuffer();
                    sb.append(record.getUnitName()).append("/(");
                    if (resVOS != null && !resVOS.isEmpty()) {
                        sb.append(resVOS.get(0).getUnitExtendName());
                        sb.append("=");
                        sb.append(resVOS.get(0).getConversionRatio());
                        sb.append(record.getUnitName()+")");
                        unitPageResVO.setDeputyUnitName(resVOS.get(0).getUnitExtendName());
                        if (resVOS.size() >=2){
                            sb.append("/(");
                            sb.append(resVOS.get(1).getUnitExtendName());
                            sb.append("=");
                            sb.append(resVOS.get(1).getConversionRatio());
                            sb.append(record.getUnitName()+")");
                            unitPageResVO.setDeputyUnitName2(resVOS.get(1).getUnitExtendName());
                        }
                        if (resVOS.size() >=3){
                            sb.append("/(");
                            sb.append(resVOS.get(2).getUnitExtendName());
                            sb.append("=");
                            sb.append(resVOS.get(2).getConversionRatio());
                            sb.append(record.getUnitName()+")");
                            unitPageResVO.setDeputyUnitName3(resVOS.get(2).getUnitExtendName());
                        }
                    }
                    unitPageResVO.setUnitName(sb.toString());
                    res.add(unitPageResVO);
                });
            }
            pageResult.setTotal(iPage.getTotal());
            pageResult.setPageList(res);
        }
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
        goodsBaseUnitDO.setUpdateBy(SecurityUtils.getUserId());
        goodsBaseUnitDO.setUpdateTime(LocalDateTime.now());
        goodsBaseUnitDO.setUpdateByName(SecurityUtils.getUsername());
        goodsBaseUnitDao.updateById(goodsBaseUnitDO);
        if (updateUnitVO.getExtendList() == null || updateUnitVO.getExtendList().isEmpty()) {
            return CommonResult.success();
        }
        goodsBaseUnitExtendDao.deleteByGoodsBaseUnitId(updateUnitVO.getId());
        List<CreateUnitExtendVO> extendList = updateUnitVO.getExtendList();
        List<GoodsBaseUnitExtendDO> unitExtendVOList = NetonBeanUtils.toBean(extendList, GoodsBaseUnitExtendDO.class);
        unitExtendVOList.forEach(unitExtendDO -> {
            unitExtendDO.setUnitId(goodsBaseUnitDO.getId());
            unitExtendDO.setUpdateBy(SecurityUtils.getUserId());
            unitExtendDO.setUpdateTime(LocalDateTime.now());
            unitExtendDO.setUpdateByName(SecurityUtils.getUsername());
            unitExtendDO.setCreateByName(SecurityUtils.getUsername());
            unitExtendDO.setIsDeleted(0);
            unitExtendDO.setCreateTime(LocalDateTime.now());
            unitExtendDO.setCreateBy(SecurityUtils.getUserId());
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

    /**
     * 批量根据类型删除、禁用、启用
     *
     * @param updateUnitStatusReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> updateGoodsBaseUnitByType(UpdateUnitStatusReqVO updateUnitStatusReqVO) {
        if (updateUnitStatusReqVO.getType() == null) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_UNIT_TYPE_IS_ERR);
        }
        if (updateUnitStatusReqVO.getType() <= 0 || updateUnitStatusReqVO.getType() > 3) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_UNIT_TYPE_IS_ERR);
        }
        //批量删除
        if (updateUnitStatusReqVO.getType() == 1) {
            goodsBaseUnitDao.deleteByIds(updateUnitStatusReqVO.getIds());
        }else {
            List<GoodsBaseUnitDO> list = new ArrayList<>();
            for (Long id : updateUnitStatusReqVO.getIds()) {
                GoodsBaseUnitDO goodsBaseUnitDO = new GoodsBaseUnitDO();
                goodsBaseUnitDO.setId(id);
                goodsBaseUnitDO.setUpdateBy(SecurityUtils.getUserId());
                goodsBaseUnitDO.setUpdateTime(LocalDateTime.now());
                goodsBaseUnitDO.setUpdateByName(SecurityUtils.getUsername());
                goodsBaseUnitDO.setIsDeleted(0);
                goodsBaseUnitDO.setIsEnabled(updateUnitStatusReqVO.getType() == 2 ? 1 : 0);
                list.add(goodsBaseUnitDO);
            }
           goodsBaseUnitDao.updateBatchBaGoodsBaseUnit(list);
        }
        return CommonResult.success();
    }
}
