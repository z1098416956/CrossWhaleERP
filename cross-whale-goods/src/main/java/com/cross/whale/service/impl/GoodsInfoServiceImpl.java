package com.cross.whale.service.impl;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cross.whale.bean.NetonBeanUtils;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.PageUtil;
import com.cross.whale.common.SystemErrorCodeConstants;
import com.cross.whale.dao.GoodsInfoDao;
import com.cross.whale.entity.GoodsInfoDO;
import com.cross.whale.req.*;
import com.cross.whale.res.*;
import com.cross.whale.service.*;
import com.cross.whale.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GoodsInfoServiceImpl extends ServiceImpl<GoodsInfoDao,GoodsInfoDO> implements GoodsInfoService {

    @Resource
    private GoodsInfoDao goodsInfoDao;

    @Autowired
    private GoodsInventoryService goodsInventoryService;

    @Autowired
    private GoodsAttributeInfoService goodsAttributeInfoService;

    @Autowired
    private GoodsExtendService goodsExtendService;

    @Autowired
    private GoodsAttributeService goodsAttributeService;
    /**
     * 创建商品信息
     *
     * @param createGoodsInfoReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> createGoodsInfo(CreateGoodsInfoReqVO createGoodsInfoReqVO) {

        if (createGoodsInfoReqVO.getIsUnit() == 0 && createGoodsInfoReqVO.getAttributeReq() != null && !createGoodsInfoReqVO.getAttributeReq().isEmpty()) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_INFO_ATT_ERR);
        }
        //检查商品名称是否重复
        List<GoodsInfoDO> repeat = goodsInfoDao.checkGoodsNameRepeat(createGoodsInfoReqVO.getGoodsName());
        if (repeat != null && !repeat.isEmpty()) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_INFO_NAME_IS_REPEAT);
        }
        GoodsInfoDO goodsInfoDO = new GoodsInfoDO();
        BeanUtils.copyProperties(createGoodsInfoReqVO, goodsInfoDO);
        goodsInfoDO.setGoodsStatus(0);
        goodsInfoDO.setIsEnabled(0);
        //商品信息
        goodsInfoDO.setCreateBy(SecurityUtils.getUserId());
        goodsInfoDO.setCreateTime(LocalDateTime.now());
        goodsInfoDO.setCreateByName(SecurityUtils.getUsername());
        goodsInfoDO.setIsDeleted(0);
        goodsInfoDao.insert(goodsInfoDO);
        Long id = goodsInfoDO.getId();
        //商品与仓库
        goodsInventoryService.createGoodsInventory(createGoodsInfoReqVO, id);
        // 采购最低价
        goodsAttributeInfoService.createGoodsAttributeInfo(createGoodsInfoReqVO, id);
        // 扩展信息
        goodsExtendService.createGoodsExtend(createGoodsInfoReqVO, id);
        if (createGoodsInfoReqVO.getIsUnit() == 1) {
            // 选择的属性
            goodsAttributeService.createGoodsAttributeInfo(createGoodsInfoReqVO, id);
        }
        return CommonResult.success();
    }

    /**
     * 更新商品信息
     *
     * @param updateGoodsInfoReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> updateGoodsInfo(UpdateGoodsInfoReqVO updateGoodsInfoReqVO) {

        if (updateGoodsInfoReqVO.getId() == null) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_INFO_ID_IS_NULL);
        }
        //判断名称是否重复
        List<GoodsInfoDO> repeat = goodsInfoDao.checkGoodsNameRepeat(updateGoodsInfoReqVO.getGoodsName());
        //如果商品名称大于2直接抛出异常，不允许名称重复
        if (repeat != null && !repeat.isEmpty() && repeat.size() > 2) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_INFO_NAME_IS_REPEAT);
        }
        GoodsInfoDO goodsInfoDO = goodsInfoDao.selectById(updateGoodsInfoReqVO.getId());
        //比较ID是否一样，如果不一样也抛出名称不允许重复
        if (repeat != null && !repeat.isEmpty() && goodsInfoDO.getId().longValue() != repeat.get(0).getId().longValue()) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_INFO_NAME_IS_REPEAT);
        }
        if (updateGoodsInfoReqVO.getIsUnit() == 0 && updateGoodsInfoReqVO.getAttributeReq() != null && !updateGoodsInfoReqVO.getAttributeReq().isEmpty()) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_INFO_ATT_ERR);
        }
        //更新商品信息
        NetonBeanUtils.copyProperties(updateGoodsInfoReqVO, goodsInfoDO);
        goodsInfoDO.setUpdateBy(SecurityUtils.getUserId());
        goodsInfoDO.setUpdateTime(LocalDateTime.now());
        goodsInfoDao.updateById(goodsInfoDO);
        //商品与仓库
        goodsInventoryService.updateGoodsInventory(updateGoodsInfoReqVO);
        //采购最低价
        goodsAttributeInfoService.updateGoodsAttributeInfo(updateGoodsInfoReqVO);
        // 扩展信息
        if (updateGoodsInfoReqVO.getExtendInfo() != null && !updateGoodsInfoReqVO.getExtendInfo().isEmpty()) {
            goodsExtendService.updateGoodsExtend(updateGoodsInfoReqVO);
        }
        if (updateGoodsInfoReqVO.getIsUnit() == 1) {
            // 选择的属性
            goodsAttributeService.updateGoodsAttributeInfo(updateGoodsInfoReqVO);
        }
        return CommonResult.success();
    }

    /**
     * 删除商品信息
     *
     * @param goodsId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> deleteGoodsInfo(Long goodsId) {
        GoodsInfoDO goodsInfoDO = goodsInfoDao.selectById(goodsId);
        if (goodsInfoDO == null) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_INFO_ID_IS_ERR);
        }
        goodsInfoDO.setIsDeleted(1);
        goodsInfoDO.setUpdateBy(SecurityUtils.getUserId());
        goodsInfoDO.setUpdateTime(LocalDateTime.now());
        goodsInfoDao.updateById(goodsInfoDO);
        //商品与仓库
        goodsInventoryService.deleteGoodsInventory(goodsId);
        //采购最低价
        goodsAttributeInfoService.deleteGoodsAttributeInfo(goodsId);
        //扩展信息
        goodsExtendService.deleteGoodsExtend(goodsId);
        //选择的属性
        if (goodsInfoDO.getIsUnit() == 1) {
            goodsAttributeService.deleteGoodsAttributeInfo(goodsId);
        }
        return CommonResult.success();
    }

    /**
     * 批量删除
     *
     * @param deleteBatchGoodsReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> batchDeleteGoodsInfos(DeleteBatchGoodsReqVO deleteBatchGoodsReqVO) {
        if (deleteBatchGoodsReqVO.getIds() == null || deleteBatchGoodsReqVO.getIds().isEmpty()) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_INFO_ID_IS_NULL);
        }
        List<GoodsInfoDO> infoDOS = goodsInfoDao.selectByIds(deleteBatchGoodsReqVO.getIds());
        if (infoDOS == null || infoDOS.isEmpty()) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_INFO_ID_IS_ERR);
        }
        infoDOS.stream().forEach(infoDO -> {
            infoDO.setIsDeleted(1);
            infoDO.setUpdateBy(SecurityUtils.getUserId());
            infoDO.setUpdateTime(LocalDateTime.now());
            //goodsInfoDao.updateById(infoDO);
        });
        updateBatchById(infoDOS);
        //商品与仓库
        goodsInventoryService.batchDeleteGoodsInventory(deleteBatchGoodsReqVO.getIds());
        //采购最低价
        goodsAttributeInfoService.batchDeleteGoodsAttributeInfos(deleteBatchGoodsReqVO.getIds());
        //扩展信息
        goodsExtendService.batchDeleteGoodsExtend(deleteBatchGoodsReqVO.getIds());
        //选择的属性
        goodsAttributeService.batchDeleteGoodsAttributeInfos(deleteBatchGoodsReqVO.getIds());
        return CommonResult.success();
    }

    /**
     * 获取商品信息详情
     *
     * @param goodsId
     * @return
     */
    @Override
    public CommonResult<GoodsInfoDetailsResVO> getGoodsInfoDetailsById(Long goodsId) {
        if (goodsId == null) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_INFO_ID_IS_NULL);
        }
        GoodsInfoDO goodsInfoDO = goodsInfoDao.selectById(goodsId);
        if (goodsInfoDO == null) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_INFO_ID_IS_ERR);
        }
        GoodsInfoDetailsResVO goodsInfoDetailsResVO = new GoodsInfoDetailsResVO();
        BeanUtils.copyProperties(goodsInfoDO, goodsInfoDetailsResVO);
        //商品与仓库
        List<GoodsInventoryDetailsResVO> list = goodsInventoryService.getGoodsInventoryList(goodsId);
        goodsInfoDetailsResVO.setInventoryInfo(list);
        //采购最低价
        List<GoodsAttributeInfoDetailsResVO> goodsAttributeInfoDetails = goodsAttributeInfoService.getGoodsAttributeInfoDetails(goodsId);
        goodsInfoDetailsResVO.setAttributes(goodsAttributeInfoDetails);
        //扩展信息
        List<GoodsExtendDetailsResVO> goodsExtendDetails = goodsExtendService.getGoodsExtendDetails(goodsId);
        goodsInfoDetailsResVO.setExtendInfo(goodsExtendDetails);
        //选择的属性
        if (goodsInfoDO.getIsUnit() == 1) {
            List<GoodsAttributeDetailsResVO> goodsAttributeDetailsByGoodsId = goodsAttributeService.getGoodsAttributeDetailsByGoodsId(goodsId);
            goodsInfoDetailsResVO.setAttributeRes(goodsAttributeDetailsByGoodsId);
        }
        return CommonResult.success(goodsInfoDetailsResVO);
    }

    /**
     * 更新商品状态
     *
     * @param updateGoodsInfoStatusReqVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> updateGoodsInfoStatus(UpdateGoodsInfoStatusReqVO updateGoodsInfoStatusReqVO) {
        if (updateGoodsInfoStatusReqVO.getGoodsIds() == null || updateGoodsInfoStatusReqVO.getGoodsIds().isEmpty()) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_INFO_ID_IS_NULL);
        }
        List<GoodsInfoDO> goodsInfoDOS = goodsInfoDao.selectByIds(updateGoodsInfoStatusReqVO.getGoodsIds());
        if (goodsInfoDOS == null || goodsInfoDOS.isEmpty()) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_INFO_ID_IS_ERR);
        }
        goodsInfoDOS.forEach(goodsInfoDO -> {
            goodsInfoDO.setUpdateBy(SecurityUtils.getUserId());
            goodsInfoDO.setUpdateTime(LocalDateTime.now());
            goodsInfoDO.setUpdateByName(SecurityUtils.getUsername());
            goodsInfoDO.setIsEnabled(updateGoodsInfoStatusReqVO.getIsEnabled());
        });
        baseMapper.updateById(goodsInfoDOS);
        return CommonResult.success();
    }

    /**
     * 商品分页查询
     *
     * @param queryGoodsInfoReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<GoodsInfoPageResVO>> queryGoodsInfoPage(QueryGoodsInfoReqVO queryGoodsInfoReqVO) {
        IPage<GoodsInfoPageResVO> page = new Page<>();
        page.setCurrent(queryGoodsInfoReqVO.getPage());
        page.setSize(queryGoodsInfoReqVO.getSize());

        IPage<GoodsInfoPageResVO> iPage = goodsInfoDao.queryGoodsInfoPage(page, queryGoodsInfoReqVO);
        PageUtil<GoodsInfoPageResVO> pageUtil = new PageUtil<>();
        if (!CollectionUtils.isEmpty(iPage.getRecords())) {
            //库存
            Set<Long> goodsIds = iPage.getRecords().stream().map(GoodsInfoPageResVO::getId).collect(Collectors.toSet());
            Map<Long, Long> inventoryCount = goodsInventoryService.getGoodsInventoryCount(goodsIds);
            iPage.getRecords().forEach(goodsInfoPageResVO -> {
                Long aLong = inventoryCount.get(goodsInfoPageResVO.getId());
                if (aLong != null){
                    goodsInfoPageResVO.setCurrentStock(aLong);
                }
            });
        }
        pageUtil.setPageList(iPage.getRecords());
        pageUtil.setTotal(iPage.getTotal());

        return CommonResult.success(pageUtil);
    }

    /**
     * 请购单查询商品
     *
     * @param queryGoodsInfoReqVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<GoodsInfoReceiptsPageResVO>> queryGoodsReceiptsPage(QueryGoodsInfoReqVO queryGoodsInfoReqVO) {
        IPage<GoodsInfoReceiptsPageResVO> page = new Page<>();
        page.setCurrent(queryGoodsInfoReqVO.getPage());
        page.setSize(queryGoodsInfoReqVO.getSize());
        IPage<GoodsInfoReceiptsPageResVO> iPage = goodsInfoDao.queryGoodsReceiptsPage(page, queryGoodsInfoReqVO);
        PageUtil<GoodsInfoReceiptsPageResVO> pageUtil = new PageUtil<>();
        if (!CollectionUtils.isEmpty(iPage.getRecords())) {
            //库存
            Set<Long> goodsIds = iPage.getRecords().stream().map(GoodsInfoReceiptsPageResVO::getId).collect(Collectors.toSet());
            Map<Long, Long> inventoryCount = goodsInventoryService.getGoodsInventoryCount(goodsIds);
            //扩展信息
            Map<Long, List<String>> extendInfo = goodsExtendService.getGoodsExtendInfo(goodsIds);
            // SKU
            Map<Long, List<GoodsAttributeInfoDetailsResVO>> info = goodsAttributeInfoService.getGoodsAttributeInfoByGoodsIds(goodsIds);
            iPage.getRecords().forEach(goodsInfoPageResVO -> {
                Long aLong = inventoryCount.get(goodsInfoPageResVO.getId());
                if (aLong != null){
                    goodsInfoPageResVO.setCurrentStock(aLong);
                }
                List<String> list = extendInfo.get(goodsInfoPageResVO.getId());
                if (list != null && !list.isEmpty()) {
                    goodsInfoPageResVO.setGoodsExtend(list);
                }
                List<GoodsAttributeInfoDetailsResVO> voList = info.get(goodsInfoPageResVO.getId());
                if (voList != null && !voList.isEmpty()) {
                    goodsInfoPageResVO.setGoodsSKU(voList);
                }
            });
        }
        pageUtil.setPageList(iPage.getRecords());
        pageUtil.setTotal(iPage.getTotal());
        return CommonResult.success(pageUtil);
    }
}
