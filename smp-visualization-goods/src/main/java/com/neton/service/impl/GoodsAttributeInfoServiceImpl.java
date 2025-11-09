package com.neton.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neton.common.CommonResult;
import com.neton.common.ServiceException;
import com.neton.common.SystemErrorCodeConstants;
import com.neton.dao.GoodsAttributeInfoDao;
import com.neton.entity.GoodsAttributeInfoDO;
import com.neton.req.CreateGoodsInfoReqVO;
import com.neton.req.UpdateGoodsInfoReqVO;
import com.neton.res.GoodsAttributeInfoDetailsResVO;
import com.neton.service.GoodsAttributeInfoService;
import com.neton.utils.SecurityUtils;

import jakarta.annotation.Resource;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.neton.bean.NetonBeanUtils;


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
}
