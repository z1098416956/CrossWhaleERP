package com.cross.whale.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UserWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserSecurityInterceptor())
                .addPathPatterns("/**") // 拦截所有路径
                .excludePathPatterns("/v1/oauth/login","/logout", "/v1/oauth/logout"); // 排除某些路径
    }
}
