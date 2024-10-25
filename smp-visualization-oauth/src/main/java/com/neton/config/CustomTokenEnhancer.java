package com.neton.config;

import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

/**
 * @author TheSunshine
 * @date 2024-10-25 10:47:57
 */
@Component
public class CustomTokenEnhancer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    @Resource
    private UserDetailsService userDetailService;
    @Override
    public void customize(JwtEncodingContext context) {
        UserDetails user = userDetailService.loadUserByUsername(context.getPrincipal().getName());
        if (user != null) {
            context.getClaims().claims(claims -> {
                claims.put("loginName", user.getUsername());
                claims.put("name", user.getUsername());
                claims.put("content", "在accessToken中封装自定义信息");
                claims.put("authorities", "hahahaha");
            });
        }
    }

}
