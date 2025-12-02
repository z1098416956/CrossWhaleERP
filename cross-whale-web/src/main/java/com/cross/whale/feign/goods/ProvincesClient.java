package com.cross.whale.feign.goods;

import com.cross.whale.common.CommonResult;
import com.cross.whale.feign.FeignConfig;
import com.cross.whale.res.ProvincesVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "cross-whale-goods" ,configuration = FeignConfig.class,contextId = "provinces-service")
public interface ProvincesClient {

    @GetMapping("/v1/provinces/getProvincesInfo")
    public CommonResult<List<ProvincesVO>> getProvincesInfo();
}
