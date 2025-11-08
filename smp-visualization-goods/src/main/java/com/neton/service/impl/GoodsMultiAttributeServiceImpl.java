package com.neton.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neton.bean.NetonBeanUtils;
import com.neton.common.CommonResult;
import com.neton.common.PageUtil;
import com.neton.common.SystemErrorCodeConstants;
import com.neton.dao.GoodsMultiAttributeDao;
import com.neton.entity.GoodsMultiAttributeDO;
import com.neton.req.CreateGoodsMultiAttributeReqVO;
import com.neton.req.DeleteGoodsMultiAttributeReqVO;
import com.neton.req.QueryGoodsMultiAttributeReqVO;
import com.neton.req.UpdateGoodsMultiAttributeReqVO;
import com.neton.res.GoodsMultiAttributeResVO;
import com.neton.service.GoodsMultiAttributeService;
import com.neton.utils.SecurityUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class GoodsMultiAttributeServiceImpl implements GoodsMultiAttributeService {

    @Resource
    private GoodsMultiAttributeDao goodsMultiAttributeDao;

    /**
     * 创建商品多属性
     *
     * @param createMultiAttributeVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> createGoodsMultiAttribute(CreateGoodsMultiAttributeReqVO createMultiAttributeVO) {
        if (StringUtils.isBlank(createMultiAttributeVO.getAttributeName()) || StringUtils.isBlank(createMultiAttributeVO.getAttributeValue())) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_ATT_VALUE_IS_NULL);
        }
        GoodsMultiAttributeDO bean = NetonBeanUtils.toBean(createMultiAttributeVO, GoodsMultiAttributeDO.class);
        bean.setCreateTime(LocalDateTime.now());
        bean.setCreateBy(SecurityUtils.getUserId());
        bean.setCreateByName(SecurityUtils.getUsername());
        bean.setIsDeleted(0);
        goodsMultiAttributeDao.insert(bean);
        return CommonResult.success();
    }

    /**
     * 更新商品多属性
     *
     * @param updateMultiAttributeVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> updateGoodsMultiAttribute(UpdateGoodsMultiAttributeReqVO updateMultiAttributeVO) {
        if (updateMultiAttributeVO.getId() == null){
            return CommonResult.error(SystemErrorCodeConstants.GOOD_ATT_ID_NULL);
        }
        if (StringUtils.isBlank(updateMultiAttributeVO.getAttributeName()) || StringUtils.isBlank(updateMultiAttributeVO.getAttributeValue())) {
            return CommonResult.error(SystemErrorCodeConstants.GOOD_ATT_VALUE_IS_NULL);
        }
        GoodsMultiAttributeDO goodsMultiAttributeDO = goodsMultiAttributeDao.selectById(updateMultiAttributeVO.getId());
        if (goodsMultiAttributeDO == null){
            return CommonResult.error(SystemErrorCodeConstants.GOOD_ATT_ID_ERR);
        }
        goodsMultiAttributeDO.setAttributeName(updateMultiAttributeVO.getAttributeName());
        goodsMultiAttributeDO.setAttributeValue(updateMultiAttributeVO.getAttributeValue());
        goodsMultiAttributeDO.setUpdateBy(SecurityUtils.getUserId());
        goodsMultiAttributeDO.setUpdateByName(SecurityUtils.getUsername());
        goodsMultiAttributeDO.setUpdateTime(LocalDateTime.now());
        goodsMultiAttributeDao.updateById(goodsMultiAttributeDO);
        return CommonResult.success();
    }

    /**
     * 批量删除多属性
     *
     * @param deleteMultiAttributeVO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> deleteGoodsMultiAttribute(DeleteGoodsMultiAttributeReqVO deleteMultiAttributeVO) {
        if (deleteMultiAttributeVO.getIds() == null || deleteMultiAttributeVO.getIds().isEmpty()){
            return CommonResult.error(SystemErrorCodeConstants.GOOD_ATT_ID_NULL);
        }
        goodsMultiAttributeDao.deleteByIds(deleteMultiAttributeVO.getIds());
        return CommonResult.success();
    }

    /**
     * 分页查询商品多属性
     *
     * @param queryMultiAttributeVO
     * @return
     */
    @Override
    public CommonResult<PageUtil<GoodsMultiAttributeResVO>> queryGoodsMultiAttributePage(QueryGoodsMultiAttributeReqVO queryMultiAttributeVO) {
        IPage<GoodsMultiAttributeResVO> page = new Page<>();
        page.setCurrent(queryMultiAttributeVO.getPage());
        page.setSize(queryMultiAttributeVO.getSize());
        IPage<GoodsMultiAttributeResVO> iPage = goodsMultiAttributeDao.queryGoodsMultiAttributePage(page, queryMultiAttributeVO);
        PageUtil<GoodsMultiAttributeResVO> pageUtil = new PageUtil<>();
        pageUtil.setPageList(iPage.getRecords());
        pageUtil.setTotal(iPage.getTotal());
        return CommonResult.success(pageUtil);
    }

    /**
     * 根据ID查询商品多属性
     *
     * @param id
     * @return
     */
    @Override
    public CommonResult<GoodsMultiAttributeResVO> getGoodsMultiAttributeById(Long id) {
        if (id == null){
            return CommonResult.error(SystemErrorCodeConstants.GOOD_ATT_ID_NULL);
        }
        GoodsMultiAttributeDO goodsMultiAttributeDO = goodsMultiAttributeDao.selectById(id);
        if (goodsMultiAttributeDO == null){
            return CommonResult.error(SystemErrorCodeConstants.GOOD_ATT_ID_ERR);
        }
        GoodsMultiAttributeResVO bean = NetonBeanUtils.toBean(goodsMultiAttributeDO, GoodsMultiAttributeResVO.class);
        return CommonResult.success(bean);
    }

    /**
     * 根据属性id删除商品多属性
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResult<Void> deleteGoodsMultiAttributeByAttributeId(Long id) {
        if (id == null){
            return CommonResult.error(SystemErrorCodeConstants.GOOD_ATT_ID_NULL);
        }
        GoodsMultiAttributeDO goodsMultiAttributeDO = goodsMultiAttributeDao.selectById(id);
        if (goodsMultiAttributeDO == null){
            return CommonResult.error(SystemErrorCodeConstants.GOOD_ATT_ID_ERR);
        }
      //  goodsMultiAttributeDO.setIsDeleted(1);
        goodsMultiAttributeDO.setUpdateBy(SecurityUtils.getUserId());
        goodsMultiAttributeDO.setUpdateByName(SecurityUtils.getUsername());
        goodsMultiAttributeDO.setUpdateTime(LocalDateTime.now());
        goodsMultiAttributeDao.deleteById(goodsMultiAttributeDO);
        return CommonResult.success();
    }
}
