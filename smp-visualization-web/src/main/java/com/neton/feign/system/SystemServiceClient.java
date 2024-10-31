package com.neton.feign.system;

import com.neton.commonality.common.CommonResult;
import com.neton.commonality.req.QueryAccAccountVO;
import com.neton.commonality.res.AccAccountVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author TheSunshine
 * @date 2024-10-31 14:30:04
 */
@FeignClient(name = "smp-system-service")
public interface SystemServiceClient {

    /**
     * 查询用户列表
     * @param queryAccAccountVO
     * @return
     */
    @PostMapping("/v1/account/queryAccountInfoList")
    public CommonResult<List<AccAccountVO>> queryAccountInfoList(@RequestBody QueryAccAccountVO queryAccAccountVO);
}
