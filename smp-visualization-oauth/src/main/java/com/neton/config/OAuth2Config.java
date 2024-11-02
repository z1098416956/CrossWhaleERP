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

    //解决跨域
    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")	// 允许跨域访问的路径
                .allowedOrigins("*")	// 允许跨域访问的源
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE","OPTIONS")	// 允许请求方法
                .maxAge(168000)	// 预检间隔时间
                .allowedHeaders("*")  // 允许头部设置
                .allowCredentials(true);	// 是否发送cookie
    }*/


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

/*    @Bean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate,
                                                           RegisteredClientRepository registeredClientRepository) {
        JdbcOAuth2AuthorizationService service = new JdbcOAuth2AuthorizationService(jdbcTemplate,
                registeredClientRepository);
        JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper authorizationRowMapper = new JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper(
                registeredClientRepository);
        authorizationRowMapper.setLobHandler(new DefaultLobHandler());

        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = JdbcOAuth2AuthorizationService.class.getClassLoader();
        List<Module> modules = SecurityJackson2Modules.getModules(classLoader);
        objectMapper.registerModules(modules);
        objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());
        objectMapper.addMixIn(Collections.synchronizedSet(new HashSet<>()).getClass(), CustomUserDetailsMixin.class);
        authorizationRowMapper.setObjectMapper(objectMapper);

        service.setAuthorizationRowMapper(authorizationRowMapper);
        return service;
    }*/

    @Bean
    public OAuth2AuthorizationService auth2AuthorizationService(RegisteredClientRepository registeredClientRepository, AutowireCapableBeanFactory beanFactory) {
        return new RedisOAuth2AuthorizationService(registeredClientRepository, beanFactory);
    }

}
