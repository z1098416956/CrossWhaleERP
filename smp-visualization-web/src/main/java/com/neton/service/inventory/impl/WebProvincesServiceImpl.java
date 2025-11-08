package com.neton.service.inventory.impl;

import com.neton.common.CommonResult;
import com.neton.feign.goods.ProvincesClient;
import com.neton.res.ProvincesVO;
import com.neton.service.inventory.WebProvincesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WebProvincesServiceImpl implements WebProvincesService {

    @Autowired
    private ProvincesClient provincesClient;
    /**
     * 获取城市信息
     *
     * @return
     */
    @Override
    public CommonResult<List<ProvincesVO>> getProvincesInfo() {
        return provincesClient.getProvincesInfo();
    }
}
