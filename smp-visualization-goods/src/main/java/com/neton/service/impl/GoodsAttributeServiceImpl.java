package com.neton.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neton.bean.NetonBeanUtils;
import com.neton.common.CommonResult;
import com.neton.common.ServiceException;
import com.neton.common.SystemErrorCodeConstants;
import com.neton.dao.GoodsAttributeDao;
import com.neton.req.CreateGoodsInfoReqVO;
import com.neton.req.UpdateGoodsInfoReqVO;
import com.neton.res.GoodsAttributeDetailsResVO;
import com.neton.service.GoodsAttributeService;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import com.neton.entity.GoodsAttributeDO;
import com.neton.utils.SecurityUtils;


@Service
public class GoodsAttributeServiceImpl extends ServiceImpl<GoodsAttributeDao,GoodsAttributeDO> implements GoodsAttributeService {



    /**
     * 创建商品选择的属性
     *
     * @param createGoodsInfoReqVO
     * @param goodsId
     * @return
     */
    @Override
    public CommonResult<Void> createGoodsAttributeInfo(CreateGoodsInfoReqVO createGoodsInfoReqVO, Long goodsId) {
        if (createGoodsInfoReqVO.getIsUnit() == 1 && createGoodsInfoReqVO.getAttributeReq() == null) {
            throw new ServiceException(SystemErrorCodeConstants.GOOD_INFO_MORE_ATT_ERR);
        }
        List<GoodsAttributeDO> goodsAttributeDOList = createGoodsInfoReqVO.getAttributeReq().stream().map(attributeReq -> {
            GoodsAttributeDO goodsAttributeDO = new GoodsAttributeDO();
            goodsAttributeDO.setGoodsId(goodsId);
            goodsAttributeDO.setAttributeName(attributeReq.getAttributeName());
            goodsAttributeDO.setGoodsMultiAttributeId(attributeReq.getGoodsMultiAttributeId());
            goodsAttributeDO.setCreateBy(SecurityUtils.getUserId());
            goodsAttributeDO.setUpdateBy(SecurityUtils.getUserId());
            goodsAttributeDO.setIsDeleted(0);
            goodsAttributeDO.setCreateTime(LocalDateTime.now());
            goodsAttributeDO.setUpdateTime(LocalDateTime.now());
            goodsAttributeDO.setCreateByName(SecurityUtils.getUsername());
            goodsAttributeDO.setUpdateByName(SecurityUtils.getUsername());
            return goodsAttributeDO;
        }).collect(Collectors.toList());
        baseMapper.insert(goodsAttributeDOList);
        return CommonResult.success();
    }

    /**
     * 更新商品选择的属性
     *
     * @param updateGoodsInfoReqVO
     */
    @Override
    public void updateGoodsAttributeInfo(UpdateGoodsInfoReqVO updateGoodsInfoReqVO) {
        if (updateGoodsInfoReqVO.getIsUnit() == 1 && updateGoodsInfoReqVO.getAttributeReq() == null) {
            throw new ServiceException(SystemErrorCodeConstants.GOOD_INFO_MORE_ATT_ERR);
        }
        baseMapper.deleteByGoodsId(updateGoodsInfoReqVO.getId());
        List<GoodsAttributeDO> goodsAttributeDOList = updateGoodsInfoReqVO.getAttributeReq().stream().map(attributeReq -> {
            GoodsAttributeDO goodsAttributeDO = new GoodsAttributeDO();
            goodsAttributeDO.setGoodsId(updateGoodsInfoReqVO.getId());
            goodsAttributeDO.setAttributeName(attributeReq.getAttributeName());
            goodsAttributeDO.setGoodsMultiAttributeId(attributeReq.getGoodsMultiAttributeId());
            goodsAttributeDO.setCreateBy(SecurityUtils.getUserId());
            goodsAttributeDO.setUpdateBy(SecurityUtils.getUserId());
            goodsAttributeDO.setIsDeleted(0);
            goodsAttributeDO.setCreateTime(LocalDateTime.now());
            goodsAttributeDO.setUpdateTime(LocalDateTime.now());
            goodsAttributeDO.setCreateByName(SecurityUtils.getUsername());
            goodsAttributeDO.setUpdateByName(SecurityUtils.getUsername());
            return goodsAttributeDO;
        }).collect(Collectors.toList());
        baseMapper.insert(goodsAttributeDOList);
    }

    /**
     * 删除选择的属性
     *
     * @param goodsId
     */
    @Override
    public void deleteGoodsAttributeInfo(Long goodsId) {
        List<GoodsAttributeDO> list = baseMapper.selectByGoodsId(goodsId);
        if (list == null || list.isEmpty()){
            return;
        }
        list.forEach(attributeDO -> {
            attributeDO.setIsDeleted(1);
            attributeDO.setUpdateTime(LocalDateTime.now());
            attributeDO.setUpdateByName(SecurityUtils.getUsername());
            attributeDO.setUpdateBy(SecurityUtils.getUserId());
        });
        baseMapper.updateById(list);
    }

    /**
     * 获取商品选中的属性
     *
     * @param goodsId
     * @return
     */
    @Override
    public List<GoodsAttributeDetailsResVO> getGoodsAttributeDetailsByGoodsId(Long goodsId) {
        List<GoodsAttributeDO> list = baseMapper.selectByGoodsId(goodsId);
        if (list == null || list.isEmpty()){
            return new ArrayList<>();
        }
        List<GoodsAttributeDetailsResVO> bean = NetonBeanUtils.toBean(list, GoodsAttributeDetailsResVO.class);
        return bean;
    }
}
