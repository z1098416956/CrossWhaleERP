package com.neton.service.impl;

import com.neton.common.CommonResult;
import com.neton.common.SystemErrorCodeConstants;
import com.neton.dao.GoodsInfoDao;
import com.neton.entity.GoodsInfoDO;
import com.neton.req.CreateGoodsInfoReqVO;
import com.neton.service.GoodsAttributeInfoService;
import com.neton.service.GoodsInfoService;
import com.neton.service.GoodsInventoryService;
import com.neton.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Resource
    private GoodsInfoDao goodsInfoDao;

    @Autowired
    private GoodsInventoryService goodsInventoryService;

    @Autowired
    private GoodsAttributeInfoService goodsAttributeInfoService;

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
        return null;
    }
}
