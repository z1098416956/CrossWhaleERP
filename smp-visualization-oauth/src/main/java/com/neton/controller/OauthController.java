package com.neton.controller;

import com.neton.common.CommonResult;
import com.neton.config.MD5PasswordEncoder;
import com.neton.service.CustomLoginService;
import com.nimbusds.oauth2.sdk.client.ClientRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

/**
 * @author TheSunshine
 * @date 2024-10-25 14:52:11
 */
@RestController
@RequestMapping("/v1/oauth")
public class OauthController {

    @Autowired
    private RegisteredClientRepository registeredClientRepository;

    @Autowired
    private CustomLoginService customLoginService;

    @Autowired
    private MD5PasswordEncoder passwordEncoder;
    @PostMapping("/create")
    public ResponseEntity<RegisteredClient> registerClient(@RequestBody Map<String,String> request) {
        // 创建一个新的客户端
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(request.get("clientId"))
                .clientSecret(passwordEncoder.encode(request.get("secret")))  // {noop} 表示不加密
                .scope(request.get("scope"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType( new AuthorizationGrantType("password"))
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUri("192.168.1.18")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofDays(7))
                        .refreshTokenTimeToLive(Duration.ofDays(30))
                        .build())
                .build();

        // 将客户端保存到存储中
        registeredClientRepository.save(registeredClient);

        return ResponseEntity.ok(registeredClient);
    }

    @PostMapping("/login")
    public CommonResult login(@RequestBody Map<String,String> params){

        return customLoginService.getToken(params.get("userName"),params.get("password"));
    }

    @PostMapping("/refreshToken")
    public CommonResult refreshToken(@RequestParam String refreshToken){

        return customLoginService.refreshToken(refreshToken);
    }
}
