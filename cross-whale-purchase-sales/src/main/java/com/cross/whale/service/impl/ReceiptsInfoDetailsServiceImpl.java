package com.cross.whale.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cross.whale.bean.NetonBeanUtils;
import com.cross.whale.dao.ReceiptsInfoDetailsDao;
import com.cross.whale.entity.ReceiptsInfoDetailsDO;
import com.cross.whale.req.UpdateReceiptsInfoReqVO;
import com.cross.whale.res.ReceiptsInfoDetailsResVO;
import com.cross.whale.service.ReceiptsInfoDetailsService;
import com.cross.whale.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptsInfoDetailsServiceImpl extends ServiceImpl<ReceiptsInfoDetailsDao, ReceiptsInfoDetailsDO> implements ReceiptsInfoDetailsService {
    /**
     * 获取请购单详细信息
     *
     * @param receiptsId
     * @return
     */
    @Override
    public List<ReceiptsInfoDetailsResVO> listReceiptsInfoDetails(Long receiptsId) {
        List<ReceiptsInfoDetailsDO> list = baseMapper.listReceiptsInfoDetails(receiptsId);
        List<ReceiptsInfoDetailsResVO> bean = NetonBeanUtils.toBean(list, ReceiptsInfoDetailsResVO.class);
        return bean;
    }

    /**
     * 删除请购单商品信息
     *
     * @param receiptsId
     */
    @Override
    public void deleteReceiptsInfoDetails(Long receiptsId) {
        List<ReceiptsInfoDetailsDO> list = baseMapper.listReceiptsInfoDetails(receiptsId);
        if(list == null || list.isEmpty()){
            return;
        }
        list.forEach(receiptsInfoDetailsDO -> {
            receiptsInfoDetailsDO.setIsDeleted(1);
            receiptsInfoDetailsDO.setUpdateBy(SecurityUtils.getUserId());
            receiptsInfoDetailsDO.setUpdateTime(LocalDateTime.now());
            receiptsInfoDetailsDO.setUpdateByName(SecurityUtils.getUsername());
        });
        updateBatchById(list);
    }

    /**
     * 更新单据详情信息
     *
     * @param updateReceiptsInfoReqVO
     */
    @Override
    public void updateReceiptsInfoDetails(UpdateReceiptsInfoReqVO updateReceiptsInfoReqVO) {
        if (updateReceiptsInfoReqVO.getDetails() == null) {
            return;
        }
        baseMapper.deleteReceiptsInfoDetailsByreceiptsId(updateReceiptsInfoReqVO.getId());
        List<ReceiptsInfoDetailsDO> res = updateReceiptsInfoReqVO.getDetails().stream().map(req -> {
            ReceiptsInfoDetailsDO receiptsInfoDetailsDO = new ReceiptsInfoDetailsDO();
            BeanUtils.copyProperties(req, receiptsInfoDetailsDO);
            receiptsInfoDetailsDO.setReceiptsId(updateReceiptsInfoReqVO.getId());
            receiptsInfoDetailsDO.setIsDeleted(0);
            receiptsInfoDetailsDO.setCreateBy(SecurityUtils.getUserId());
            receiptsInfoDetailsDO.setUpdateBy(SecurityUtils.getUserId());
            receiptsInfoDetailsDO.setCreateTime(LocalDateTime.now());
            receiptsInfoDetailsDO.setUpdateTime(LocalDateTime.now());
            receiptsInfoDetailsDO.setCreateByName(SecurityUtils.getUsername());
            receiptsInfoDetailsDO.setUpdateByName(SecurityUtils.getUsername());
            return receiptsInfoDetailsDO;
        }).collect(Collectors.toList());
        saveBatch(res);
    }


}
