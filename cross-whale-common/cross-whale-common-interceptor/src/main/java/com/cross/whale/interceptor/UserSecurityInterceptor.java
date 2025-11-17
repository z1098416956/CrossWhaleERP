package com.cross.whale.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.cross.whale.constants.SecurityConstants;
import com.cross.whale.utils.HttpUtil;
import com.cross.whale.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Base64;
import java.util.HashMap;

@Component
@Slf4j
public class UserSecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        obtainUserId(request);
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清空 SecurityContext
        UserSecurityContextHolder.clear();
    }

    private Long obtainUserId(HttpServletRequest request){
        String accessToken = HttpUtil.authorization(request);
        Long userId = null;
        if (accessToken != null){
            String[] parts = accessToken.split("\\.");
            String payload = new String(Base64.getDecoder().decode(parts[1].replace("-", "+").replace("_", "/")));
            HashMap<String,Object> userJsonObject = JSONObject.parseObject(payload,HashMap.class);
            // 获得用户编号
            userId = Long.parseLong(userJsonObject.get(SecurityConstants.DETAILS_USER_ID).toString());
            String username = userJsonObject.get(SecurityConstants.DETAILS_USERNAME).toString();
            String account = userJsonObject.get(SecurityConstants.DETAILS_ACCOUNT).toString();
            SecurityUtils.setUserId(userId);
            SecurityUtils.setUsername(username);
            SecurityUtils.setAccount(account);
        }
        return userId;
    }
}
