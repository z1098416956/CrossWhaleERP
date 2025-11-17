package com.cross.whale.service.inventory;

import com.cross.whale.common.CommonResult;
import com.cross.whale.res.ProvincesVO;

import java.util.List;

public interface WebProvincesService {

    /**
     * 获取城市信息
     * @return
     */
    public CommonResult<List<ProvincesVO>> getProvincesInfo();
}
