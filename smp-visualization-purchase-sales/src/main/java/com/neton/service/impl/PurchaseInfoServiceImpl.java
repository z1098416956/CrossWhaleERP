package com.neton.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neton.dao.PurchaseInfoDao;
import com.neton.entity.PurchaseInfoDO;
import com.neton.service.PurchaseInfoService;
import org.springframework.stereotype.Service;

@Service
public class PurchaseInfoServiceImpl extends ServiceImpl<PurchaseInfoDao, PurchaseInfoDO> implements PurchaseInfoService {
}
