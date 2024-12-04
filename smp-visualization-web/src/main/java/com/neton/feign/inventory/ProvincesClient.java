package com.neton.feign.inventory;

import com.neton.common.CommonResult;
import com.neton.feign.FeignConfig;
import com.neton.res.ProvincesVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "smp-inventory-service" ,configuration = FeignConfig.class,contextId = "provinces-service")
public interface ProvincesClient {

    @GetMapping("/v1/provinces/getProvincesInfo")
    public CommonResult<List<ProvincesVO>> getProvincesInfo();
}
