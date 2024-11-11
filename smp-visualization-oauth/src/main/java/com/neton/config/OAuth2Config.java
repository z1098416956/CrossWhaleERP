package com.neton.config;

import com.neton.service.RedisOAuth2AuthorizationService;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author TheSunshine
 * @date 2024-10-29 13:37:28
 */
@Configuration
public class OAuth2Config {


    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // 设置前端的域
        config.addAllowedMethod("*"); // 允许所有方法
        config.addAllowedHeader("*"); // 允许所有头部
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
    @Bean
    public LobHandler lobHandler() {
        return new DefaultLobHandler();
    }


    @Bean
    public OAuth2AuthorizationService auth2AuthorizationService(RegisteredClientRepository registeredClientRepository, AutowireCapableBeanFactory beanFactory) {
        return new RedisOAuth2AuthorizationService(registeredClientRepository, beanFactory);
    }

}
