package com.cross.whale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.ServiceException;
import com.cross.whale.common.SystemErrorCodeConstants;
import com.cross.whale.dao.GoodsAttributeInfoDao;
import com.cross.whale.entity.GoodsAttributeInfoDO;
import com.cross.whale.req.CreateGoodsInfoReqVO;
import com.cross.whale.req.UpdateGoodsInfoReqVO;
import com.cross.whale.res.GoodsAttributeInfoDetailsResVO;
import com.cross.whale.service.GoodsAttributeInfoService;
import com.cross.whale.utils.SecurityUtils;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.cross.whale.bean.NetonBeanUtils;


@Service
public class GoodsAttributeInfoServiceImpl extends ServiceImpl<GoodsAttributeInfoDao,GoodsAttributeInfoDO> implements GoodsAttributeInfoService {



    /**
     * 创建商品多属性
     *
     * @param createGoodsInfoReqVO
     * @param goodsId
     */
    @Override
    public CommonResult<Void> createGoodsAttributeInfo(CreateGoodsInfoReqVO createGoodsInfoReqVO, Long goodsId) {
        if (createGoodsInfoReqVO.getAttributes() == null || createGoodsInfoReqVO.getAttributes().isEmpty()) {
            throw new ServiceException(SystemErrorCodeConstants.GOOD_INFO_MORE_ATT_ERR);
        }
        // 商品多属性
        List<GoodsAttributeInfoDO> goodsAttributeInfoDOList = createGoodsInfoReqVO.getAttributes().stream().map(item -> {
            GoodsAttributeInfoDO goodsAttributeInfoDO = new GoodsAttributeInfoDO();
            goodsAttributeInfoDO.setGoodsId(goodsId);
            NetonBeanUtils.copyProperties(item, goodsAttributeInfoDO);
            goodsAttributeInfoDO.setCreateBy(SecurityUtils.getUserId());
            goodsAttributeInfoDO.setCreateByName(SecurityUtils.getUsername());
            goodsAttributeInfoDO.setCreateTime(LocalDateTime.now());
            goodsAttributeInfoDO.setIsDeleted(0);
            goodsAttributeInfoDO.setUpdateBy(SecurityUtils.getUserId());
            goodsAttributeInfoDO.setUpdateByName(SecurityUtils.getUsername());
            goodsAttributeInfoDO.setUpdateTime(LocalDateTime.now());
            return goodsAttributeInfoDO;
        }).collect(Collectors.toList());
        baseMapper.insert(goodsAttributeInfoDOList);
        return CommonResult.success();
    }


    /**
     * 更新商品多属性
     *
     * @param updateGoodsInfoReqVO
     */
    @Override
    public void updateGoodsAttributeInfo(UpdateGoodsInfoReqVO updateGoodsInfoReqVO) {
        if (updateGoodsInfoReqVO.getAttributes() == null || updateGoodsInfoReqVO.getAttributes().isEmpty()) {
            throw new ServiceException(SystemErrorCodeConstants.GOOD_INFO_MORE_ATT_ERR);
        }
        baseMapper.deleteByGoodsId(updateGoodsInfoReqVO.getId());
        List<GoodsAttributeInfoDO> goodsAttributeInfoDOList = updateGoodsInfoReqVO.getAttributes().stream().map(item -> {
            GoodsAttributeInfoDO goodsAttributeInfoDO = new GoodsAttributeInfoDO();
            goodsAttributeInfoDO.setGoodsId(updateGoodsInfoReqVO.getId());
            NetonBeanUtils.copyProperties(item, goodsAttributeInfoDO);
            goodsAttributeInfoDO.setIsDeleted(0);
            goodsAttributeInfoDO.setUpdateBy(SecurityUtils.getUserId());
            goodsAttributeInfoDO.setUpdateByName(SecurityUtils.getUsername());
            goodsAttributeInfoDO.setUpdateTime(LocalDateTime.now());
            return goodsAttributeInfoDO;
        }).collect(Collectors.toList());
        baseMapper.insert(goodsAttributeInfoDOList);
    }

    /**
     * 删除商品多属性
     *
     * @param goodsId
     */
    @Override
    public void deleteGoodsAttributeInfo(Long goodsId) {
        List<GoodsAttributeInfoDO> byGoodsId = baseMapper.findByGoodsId(goodsId);
        if (byGoodsId == null || byGoodsId.isEmpty()) {
            return;
        }
        byGoodsId.forEach(item -> {
            item.setIsDeleted(1);
            item.setUpdateBy(SecurityUtils.getUserId());
            item.setUpdateTime(LocalDateTime.now());
            item.setUpdateByName(SecurityUtils.getUsername());
        });
        updateBatchById(byGoodsId);
    }

    /**
     * 批量删除
     *
     * @param goodsIds
     */
    @Override
    public void batchDeleteGoodsAttributeInfos(List<Long> goodsIds) {
        List<GoodsAttributeInfoDO> byGoodsId = baseMapper.findByGoodsIds(goodsIds);
        if (byGoodsId == null || byGoodsId.isEmpty()) {
            return;
        }
        byGoodsId.forEach(item -> {
            item.setIsDeleted(1);
            item.setUpdateBy(SecurityUtils.getUserId());
            item.setUpdateTime(LocalDateTime.now());
            item.setUpdateByName(SecurityUtils.getUsername());
        });
        updateBatchById(byGoodsId);
    }

    /**
     * 获取商品多属性
     *
     * @param goodsId
     * @return
     */
    @Override
    public List<GoodsAttributeInfoDetailsResVO> getGoodsAttributeInfoDetails(Long goodsId) {
        List<GoodsAttributeInfoDO> byGoodsId = baseMapper.findByGoodsId(goodsId);
        if (byGoodsId == null || byGoodsId.isEmpty()) {
            return new ArrayList<>();
        }
        List<GoodsAttributeInfoDetailsResVO> bean = NetonBeanUtils.toBean(byGoodsId, GoodsAttributeInfoDetailsResVO.class);
        return bean;
    }

    /**
     * 获取商品多属性
     *
     * @param goodsId
     * @return
     */
    @Override
    public Map<Long,List<GoodsAttributeInfoDetailsResVO>> getGoodsAttributeInfoByGoodsIds(Set<Long> goodsId) {
        List<GoodsAttributeInfoDO> byGoodsId = baseMapper.findByGoodsIds(goodsId);
        if (byGoodsId == null || byGoodsId.isEmpty()) {
            return new HashMap<>();
        }
        Map<Long,List<GoodsAttributeInfoDetailsResVO>> map = new HashMap<>();

        Map<Long, List<GoodsAttributeInfoDO>> collect = byGoodsId.stream().collect(Collectors.groupingBy(GoodsAttributeInfoDO::getGoodsId));
        for (Long itemId : goodsId) {
            List<GoodsAttributeInfoDO> vos = collect.get(itemId);
            if (vos == null || vos.isEmpty()) {
                continue;
            }
            List<GoodsAttributeInfoDetailsResVO> res = NetonBeanUtils.toBean(vos, GoodsAttributeInfoDetailsResVO.class);
            map.put(itemId,res);
        }
        return map;
    }
}
