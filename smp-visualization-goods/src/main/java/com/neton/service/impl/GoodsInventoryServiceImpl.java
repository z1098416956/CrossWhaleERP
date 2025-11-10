package com.neton.service.impl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neton.bean.NetonBeanUtils;
import com.neton.common.CommonResult;
import com.neton.common.ServiceException;
import com.neton.common.SystemErrorCodeConstants;
import com.neton.dao.GoodsInventoryDao;
import com.neton.entity.GoodsInventoryDO;
import com.neton.req.CreateGoodsInfoReqVO;
import com.neton.req.UpdateGoodsAttributeReqVO;
import com.neton.req.UpdateGoodsInfoReqVO;
import com.neton.req.UpdateGoodsInventoryReqVO;
import com.neton.res.GoodsInventoryDetailsResVO;
import com.neton.service.GoodsInventoryService;

import com.neton.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class GoodsInventoryServiceImpl extends ServiceImpl<GoodsInventoryDao,GoodsInventoryDO> implements GoodsInventoryService {


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
        baseMapper.insert(goodsInventoryDOList);
        return CommonResult.success();
    }

    /**
     * 更新商品与仓库
     *
     * @param updateGoodsInfoReqVO
     */
    @Override
    public void updateGoodsInventory(UpdateGoodsInfoReqVO updateGoodsInfoReqVO) {
        if (updateGoodsInfoReqVO.getInventoryInfo() == null || updateGoodsInfoReqVO.getInventoryInfo().isEmpty()) {
            throw new ServiceException (SystemErrorCodeConstants.GOOD_INFO_INVENTORY_ERR);
        }
        List<UpdateGoodsInventoryReqVO> attributeReq = updateGoodsInfoReqVO.getInventoryInfo();

        // 根据id是否为空分为两组
        List<UpdateGoodsInventoryReqVO> newInventoryList = attributeReq.stream()
                .filter(inventory -> inventory.getId() == null)
                .collect(Collectors.toList());

        List<UpdateGoodsInventoryReqVO> existingInventoryList = attributeReq.stream()
                .filter(inventory -> inventory.getId() != null)
                .collect(Collectors.toList());

        // 处理新增的库存记录
        if (!newInventoryList.isEmpty()) {
            List<GoodsInventoryDO> newInventoryDOList = newInventoryList.stream().map(inventory -> {
                GoodsInventoryDO goodsInventoryDO = new GoodsInventoryDO();
                goodsInventoryDO.setGoodsId(updateGoodsInfoReqVO.getId());
                goodsInventoryDO.setCurrentStock(inventory.getFirstCount());
                goodsInventoryDO.setHighCount(inventory.getHighCount());
                goodsInventoryDO.setFirstCount(inventory.getFirstCount());
                goodsInventoryDO.setStorageId(inventory.getStorageId());
                goodsInventoryDO.setLowCount(inventory.getLowCount());
                goodsInventoryDO.setUpdateBy(SecurityUtils.getUserId());
                goodsInventoryDO.setUpdateByName(SecurityUtils.getUsername());
                goodsInventoryDO.setUpdateTime(LocalDateTime.now());
                goodsInventoryDO.setIsDeleted(0);
                return goodsInventoryDO;
            }).collect(Collectors.toList());
            baseMapper.insert(newInventoryDOList);
        }

        // 处理已存在的库存记录
        if (!existingInventoryList.isEmpty()) {
            List<GoodsInventoryDO> collect = existingInventoryList.stream().map(inventory -> {
                GoodsInventoryDO updateInventory = new GoodsInventoryDO();
                updateInventory.setId(inventory.getId());
                updateInventory.setCurrentStock(inventory.getFirstCount());
                updateInventory.setHighCount(inventory.getHighCount());
                updateInventory.setFirstCount(inventory.getFirstCount());
                updateInventory.setStorageId(inventory.getStorageId());
                updateInventory.setLowCount(inventory.getLowCount());
                updateInventory.setUpdateBy(SecurityUtils.getUserId());
                updateInventory.setUpdateByName(SecurityUtils.getUsername());
                updateInventory.setUpdateTime(LocalDateTime.now());
                return updateInventory;
            }).collect(Collectors.toList());
            updateBatchById(collect);
        }
    }

    /**
     * 删除商品与库存
     *
     * @param goodsId
     */
    @Override
    public void deleteGoodsInventory(Long goodsId) {
        List<GoodsInventoryDO> list = baseMapper.queryGoodsInventory(goodsId);
        if (list == null || list.isEmpty()) {
            return;
        }
        list.forEach(goodsInventoryDO -> {
            goodsInventoryDO.setIsDeleted(1);
            goodsInventoryDO.setUpdateBy(SecurityUtils.getUserId());
            goodsInventoryDO.setUpdateTime(LocalDateTime.now());
            goodsInventoryDO.setUpdateByName(SecurityUtils.getUsername());
        });
        baseMapper.updateById(list);
    }

    /**
     * 批量删除
     *
     * @param goodsIds
     */
    @Override
    public void batchDeleteGoodsInventory(List<Long> goodsIds) {
        List<GoodsInventoryDO> list = baseMapper.queryGoodsInventoryByGoodsIds(goodsIds);
        if (list == null || list.isEmpty()) {
            return;
        }
        list.forEach(goodsInventoryDO -> {
            goodsInventoryDO.setIsDeleted(1);
            goodsInventoryDO.setUpdateBy(SecurityUtils.getUserId());
            goodsInventoryDO.setUpdateTime(LocalDateTime.now());
            goodsInventoryDO.setUpdateByName(SecurityUtils.getUsername());
        });
        baseMapper.updateById(list);
    }

    /**
     * 获取商品库存列表
     *
     * @param goodsId
     * @return
     */
    @Override
    public List<GoodsInventoryDetailsResVO> getGoodsInventoryList(Long goodsId) {
        List<GoodsInventoryDO> list = baseMapper.queryGoodsInventory(goodsId);
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        List<GoodsInventoryDetailsResVO> bean = NetonBeanUtils.toBean(list, GoodsInventoryDetailsResVO.class);
        return bean;
    }

    /**
     * 获取商品库存
     *
     * @param goodsIds
     * @return
     */
    @Override
    public Map<Long, Long> getGoodsInventoryCount(Set<Long> goodsIds) {
        List<GoodsInventoryDO> list = baseMapper.queryGoodsInventoryByGoodsIds(goodsIds);
        Map<Long, Long> map = new HashMap<>();
        if (list == null || list.isEmpty()) {
            return map;
        }
        Map<Long, List<GoodsInventoryDO>> collect = list.stream().collect(Collectors.groupingBy(GoodsInventoryDO::getGoodsId));
        for (Long goodsId : goodsIds) {
            List<GoodsInventoryDO> goodsInventoryDOList = collect.get(goodsId);
            if (goodsInventoryDOList == null || goodsInventoryDOList.isEmpty()) {
                continue;
            }
            // 计算该商品在所有仓库中的库存总和
            long totalStock = goodsInventoryDOList.stream()
                    .mapToLong(inventory -> inventory.getCurrentStock() != null ? inventory.getCurrentStock() : 0L)
                    .sum();
            map.put(goodsId, totalStock);
        }
        return map;
    }
}
