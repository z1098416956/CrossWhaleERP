package com.cross.whale.service.inventory.impl;

import com.cross.whale.common.CommonResult;
import com.cross.whale.feign.goods.ProvincesClient;
import com.cross.whale.res.ProvincesVO;
import com.cross.whale.service.inventory.WebProvincesService;
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
