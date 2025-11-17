package com.cross.whale.service.impl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cross.whale.bean.NetonBeanUtils;
import com.cross.whale.common.CommonResult;
import com.cross.whale.common.ServiceException;
import com.cross.whale.dao.GoodsExtendDao;
import com.cross.whale.entity.GoodsExtendDO;
import com.cross.whale.req.CreateGoodsInfoReqVO;
import com.cross.whale.req.UpdateGoodsInfoReqVO;
import com.cross.whale.res.GoodsExtendDetailsResVO;
import com.cross.whale.service.GoodsExtendService;
import com.cross.whale.utils.SecurityUtils;

import org.springframework.stereotype.Service;

import com.cross.whale.common.SystemErrorCodeConstants;

@Service
public class GoodsExtendServiceImpl extends ServiceImpl<GoodsExtendDao,GoodsExtendDO> implements GoodsExtendService {


    /**
     * 创建商品扩展信息
     *
     * @param createGoodsInfoReqVO
     * @param goodsId
     * @return
     */
    @Override
    public CommonResult<Void> createGoodsExtend(CreateGoodsInfoReqVO createGoodsInfoReqVO, Long goodsId) {
        if(createGoodsInfoReqVO.getExtendInfo() == null || createGoodsInfoReqVO.getExtendInfo().isEmpty()){
          // throw new ServiceException(SystemErrorCodeConstants.GOOD_INFO_EXTEND_IS_NULL);
            return CommonResult.success();
        }
        List<GoodsExtendDO> goodsExtendDOList = createGoodsInfoReqVO.getExtendInfo().stream().map(item -> {
            GoodsExtendDO goodsExtendDO = new GoodsExtendDO();
            goodsExtendDO.setGoodsId(goodsId);
            goodsExtendDO.setGoodsValue(item.getGoodsValue());
            goodsExtendDO.setCreateBy(SecurityUtils.getUserId());
            goodsExtendDO.setUpdateBy(SecurityUtils.getUserId());
            goodsExtendDO.setCreateTime(LocalDateTime.now());
            goodsExtendDO.setUpdateTime(LocalDateTime.now());
            goodsExtendDO.setCreateByName(SecurityUtils.getUsername());
            goodsExtendDO.setUpdateByName(SecurityUtils.getUsername());
            goodsExtendDO.setUpdateTime(LocalDateTime.now());
            goodsExtendDO.setIsDeleted(0);
            return goodsExtendDO;
        }).collect(Collectors.toList());
        baseMapper.insert(goodsExtendDOList);
        return CommonResult.success();
    }

    @Override
    public void updateGoodsExtend(UpdateGoodsInfoReqVO updateGoodsInfoReqVO) {
        if(updateGoodsInfoReqVO.getExtendInfo() == null || updateGoodsInfoReqVO.getExtendInfo().isEmpty()){
            throw new ServiceException(SystemErrorCodeConstants.GOOD_INFO_EXTEND_IS_NULL);
        }
        baseMapper.deleteByGoodsId(updateGoodsInfoReqVO.getId());
        List<GoodsExtendDO> goodsExtendDOList = updateGoodsInfoReqVO.getExtendInfo().stream().map(item -> {
            GoodsExtendDO goodsExtendDO = new GoodsExtendDO();
            goodsExtendDO.setGoodsId(updateGoodsInfoReqVO.getId());
            goodsExtendDO.setGoodsValue(item.getGoodsValue());
            goodsExtendDO.setCreateBy(SecurityUtils.getUserId());
            goodsExtendDO.setUpdateBy(SecurityUtils.getUserId());
            goodsExtendDO.setCreateTime(LocalDateTime.now());
            goodsExtendDO.setUpdateTime(LocalDateTime.now());
            goodsExtendDO.setCreateByName(SecurityUtils.getUsername());
            goodsExtendDO.setUpdateByName(SecurityUtils.getUsername());
            goodsExtendDO.setUpdateTime(LocalDateTime.now());
            goodsExtendDO.setIsDeleted(0);
            return goodsExtendDO;
        }).collect(Collectors.toList());
        baseMapper.insert(goodsExtendDOList);
    }

    /**
     * 删除商品扩展信息
     *
     * @param goodsId
     */
    @Override
    public void deleteGoodsExtend(Long goodsId) {
        List<GoodsExtendDO> list = baseMapper.selectByGoodsId(goodsId);
        if (list == null || list.isEmpty()) {
            return;
        }
        list.forEach(item -> {
            item.setIsDeleted(1);
            item.setUpdateBy(SecurityUtils.getUserId());
            item.setUpdateTime(LocalDateTime.now());
            item.setUpdateByName(SecurityUtils.getUsername());
        });
        baseMapper.updateById(list);
    }

    /**
     * 批量删除
     *
     * @param goodsIds
     */
    @Override
    public void batchDeleteGoodsExtend(List<Long> goodsIds) {
        List<GoodsExtendDO> list = baseMapper.selectByGoodsIds(goodsIds);
        if (list == null || list.isEmpty()) {
            return;
        }
        list.forEach(item -> {
            item.setIsDeleted(1);
            item.setUpdateBy(SecurityUtils.getUserId());
            item.setUpdateTime(LocalDateTime.now());
            item.setUpdateByName(SecurityUtils.getUsername());
        });
        baseMapper.updateById(list);
    }

    /**
     * 获取商品扩展信息
     *
     * @param goodsId
     * @return
     */
    @Override
    public List<GoodsExtendDetailsResVO> getGoodsExtendDetails(Long goodsId) {
        List<GoodsExtendDO> list = baseMapper.selectByGoodsId(goodsId);
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        List<GoodsExtendDetailsResVO> bean = NetonBeanUtils.toBean(list, GoodsExtendDetailsResVO.class);
        return bean;
    }

    /**
     * 获取商品扩展信息
     *
     * @param goodsIds
     * @return
     */
    @Override
    public Map<Long, List<String>> getGoodsExtendInfo(Set<Long> goodsIds) {
        List<GoodsExtendDO> list = baseMapper.selectByGoodsIds(goodsIds);
        Map<Long, List<String>> map = new HashMap<>();
        if (list == null || list.isEmpty()) {
            return map;
        }
        Map<Long, List<GoodsExtendDO>> collect = list.stream().collect(Collectors.groupingBy(GoodsExtendDO::getGoodsId));
        for (Long goodsId : goodsIds) {
            List<GoodsExtendDO> extendDOList = collect.get(goodsId);
            if (extendDOList == null || extendDOList.isEmpty()) {
                continue;
            }

            List<String> str = extendDOList.stream().map(extendDO -> extendDO.getGoodsValue()).collect(Collectors.toList());
            map.put(goodsId, str);
        }
        return map;
    }
}
