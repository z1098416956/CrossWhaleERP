package com.neton.service.impl;

import com.neton.bean.NetonBeanUtils;
import com.neton.common.CommonResult;
import com.neton.common.SystemErrorCodeConstants;
import com.neton.dao.GoodsInfoDao;
import com.neton.entity.GoodsInfoDO;
import com.neton.req.CreateGoodsInfoReqVO;
import com.neton.req.UpdateGoodsInfoReqVO;
import com.neton.service.*;
import com.neton.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {

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

        if (createGoodsInfoReqVO.getIsUnit() == 0 && createGoodsInfoReqVO.getAttributeReq() != null && !createGoodsInfoReqVO.getAttributes().isEmpty()) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_INFO_ATT_ERR);
        }
        //检查商品名称是否重复
        List<GoodsInfoDO> repeat = goodsInfoDao.checkGoodsNameRepeat(createGoodsInfoReqVO.getGoodsName());
        if (repeat != null && !repeat.isEmpty()) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_INFO_NAME_IS_REPEAT);
        }
        GoodsInfoDO goodsInfoDO = new GoodsInfoDO();
        BeanUtils.copyProperties(createGoodsInfoReqVO, goodsInfoDO);
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
        if (updateGoodsInfoReqVO.getIsUnit() == 0 && updateGoodsInfoReqVO.getAttributeReq() != null && !updateGoodsInfoReqVO.getAttributes().isEmpty()) {
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
        goodsExtendService.updateGoodsExtend(updateGoodsInfoReqVO);
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
}
