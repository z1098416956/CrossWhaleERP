package com.neton.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neton.bean.NetonBeanUtils;
import com.neton.common.CommonResult;
import com.neton.common.ServiceException;
import com.neton.dao.GoodsExtendDao;
import com.neton.entity.GoodsExtendDO;
import com.neton.req.CreateGoodsInfoReqVO;
import com.neton.req.UpdateGoodsInfoReqVO;
import com.neton.res.GoodsExtendDetailsResVO;
import com.neton.service.GoodsExtendService;
import com.neton.utils.SecurityUtils;

import org.springframework.stereotype.Service;

import com.neton.common.SystemErrorCodeConstants;

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
           throw new ServiceException(SystemErrorCodeConstants.GOOD_INFO_EXTEND_IS_NULL);
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
}
