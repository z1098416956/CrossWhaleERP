package com.neton.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.neton.common.CommonResult;
import com.neton.common.ServiceException;
import com.neton.common.SystemErrorCodeConstants;
import com.neton.dao.GoodsInventoryDao;
import com.neton.entity.GoodsInventoryDO;
import com.neton.req.CreateGoodsInfoReqVO;
import com.neton.service.GoodsInventoryService;

import com.neton.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class GoodsInventoryServiceImpl implements GoodsInventoryService {

    @Resource
    private GoodsInventoryDao goodsInventoryDao;

    /**
     * 创建商品与仓库关系
     *
     * @param createGoodsInfoReqVO
     * @param goodsId
     * @return
     */
    @Override
    public CommonResult<Void> createGoodsInventory(CreateGoodsInfoReqVO createGoodsInfoReqVO, Long goodsId) {
        if (createGoodsInfoReqVO.getInventoryInfo() == null || createGoodsInfoReqVO.getInventoryInfo().isEmpty()) {
          //  return CommonResult.error(SystemErrorCodeConstants.GOOD_INFO_INVENTORY_ERR);
            throw new ServiceException (SystemErrorCodeConstants.GOOD_INFO_INVENTORY_ERR);
        }
        List<GoodsInventoryDO> goodsInventoryDOList = createGoodsInfoReqVO.getInventoryInfo().stream().map(inventoryInfo -> {
            GoodsInventoryDO goodsInventoryDO = new GoodsInventoryDO();
            goodsInventoryDO.setGoodsId(goodsId);
            goodsInventoryDO.setCurrentStock(inventoryInfo.getFirstCount());
            goodsInventoryDO.setHighCount(inventoryInfo.getHighCount());
            goodsInventoryDO.setFirstCount(inventoryInfo.getFirstCount());
            goodsInventoryDO.setStorageId(inventoryInfo.getStorageId());
            goodsInventoryDO.setLowCount(inventoryInfo.getLowCount());
            goodsInventoryDO.setCreateBy(SecurityUtils.getUserId());
            goodsInventoryDO.setCreateByName(SecurityUtils.getUsername());
            goodsInventoryDO.setIsDeleted(0);
            goodsInventoryDO.setCreateTime(LocalDateTime.now());
            return goodsInventoryDO;
        }).collect(Collectors.toList());
        goodsInventoryDao.insert(goodsInventoryDOList);
        return CommonResult.success();
    }
}
