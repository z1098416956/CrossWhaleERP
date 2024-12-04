package com.neton.service.inventory;

import com.neton.common.CommonResult;
import com.neton.res.ProvincesVO;

import java.util.List;

public interface WebProvincesService {

    /**
     * 获取城市信息
     * @return
     */
    public CommonResult<List<ProvincesVO>> getProvincesInfo();
}
