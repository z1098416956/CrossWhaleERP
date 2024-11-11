package com.neton.service;

import com.neton.common.CommonResult;
import com.neton.common.SystemErrorCodeConstants;
import com.neton.entity.AccAccountDO;
import com.neton.util.JwtUtils;
import com.neton.utils.BCryptUtils;
import com.nimbusds.jose.jwk.source.JWKSource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Principal;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

/**
 * @author TheSunshine
 * @date 2024-10-29 11:50:11
 */
@Slf4j
@Service
public class CustomLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OAuth2AuthorizationService authorizationService;

    @Autowired
    private RegisteredClientRepository registeredClientRepository;

    @Autowired
    private JWKSource jwkSource;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 账户密码登录
     * @param userName
     * @param password
     * @return
     */
    public CommonResult<Map<String, Object>> getToken(String userName, String password) {
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(userName)) {
            return CommonResult.error(SystemErrorCodeConstants.OAUTH2_TOKEN_ACCOUNT_ISNULL);
        }
        try {

            AccAccountDO userDetails = (AccAccountDO)userDetailsService.loadUserByUsername(userName);
            if (userDetails.getEnabled() ==  false){
                return CommonResult.error(SystemErrorCodeConstants.OAUTH2_ACCOUNT_IS_ENABLE);
            }
            if (StringUtils.isEmpty(userDetails.getPassword())){
                return CommonResult.error(SystemErrorCodeConstants.OAUTH2_TOKEN_PWD_ERROR);
            }
            if (!BCryptUtils.matchesPassword(password,userDetails.getPassword())){
                return CommonResult.error(SystemErrorCodeConstants.OAUTH2_TOKEN_ACCOUNT_ERROR);
            }
            Map<String,Object> map = new HashMap<>();
            map.put("username",userDetails.getUsername());
            map.put("account",userDetails.getAccountNo());
            map.put("id",userDetails.getId());
            // 1. 进行用户认证
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, password);
            Authentication authResult = authenticationManager.authenticate(authRequest);

            // 2. 获取注册的客户端
            RegisteredClient registeredClient = registeredClientRepository.findByClientId("client"); // 替换为客户端ID
            if (registeredClient == null) {
                return CommonResult.error(SystemErrorCodeConstants.OAUTH2_ACCESS_CLIENT_NOT_FOUND);
            }

            // 3. 创建授权对象
            OAuth2Authorization authorization = OAuth2Authorization.withRegisteredClient(registeredClient)
                    .principalName(authResult.getName())
                    .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                    .attribute(Principal.class.getName(), authResult)
                    .build();

            JwsHeader headers = JwtUtils.headers().build();
            JwtClaimsSet claims = JwtUtils.accessTokenClaims(
                    registeredClient, null, authorization.getPrincipalName(), registeredClient.getScopes(),map).build();
            JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(headers, claims);
            NimbusJwtEncoder jwtEncoder = new NimbusJwtEncoder(jwkSource);
            Jwt jwtAccessToken = jwtEncoder.encode(jwtEncoderParameters);
            Set<String> authorizedScopes = authorization.getAttribute("scopes");
            OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                    jwtAccessToken.getTokenValue(), jwtAccessToken.getIssuedAt(),
                    jwtAccessToken.getExpiresAt(), authorizedScopes);

            OAuth2AccessToken oauth2AccessToken = new OAuth2AccessToken(
                    OAuth2AccessToken.TokenType.BEARER, accessToken.getTokenValue(), Instant.now(),
                    Instant.now().plus(Duration.ofDays(7)), registeredClient.getScopes());

            // 4. 生成 refresh token
            OAuth2RefreshToken refreshToken = new OAuth2RefreshToken(
                    Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes()), Instant.now(),
                    Instant.now().plus(Duration.ofDays(14)));
            authorization = OAuth2Authorization.from(authorization)
                    .token(oauth2AccessToken)
                    .refreshToken(refreshToken)
                    .build();
            authorizationService.save(authorization);

            // 6. 返回响应
            Map<String, Object> tokens = new HashMap<>();
            tokens.put("access_token", oauth2AccessToken.getTokenValue());
            tokens.put("refresh_token", refreshToken.getTokenValue());
            tokens.put("token_type", oauth2AccessToken.getTokenType().getValue());
            tokens.put("expires_in", oauth2AccessToken.getExpiresAt().getEpochSecond() - Instant.now().getEpochSecond());
            tokens.put("refresh_expires_in", refreshToken.getExpiresAt().getEpochSecond() - Instant.now().getEpochSecond());
            return CommonResult.success(tokens);
        } catch (Exception e) {
            log.error("e ===============> :{}", e.getMessage());
            e.printStackTrace();
            return CommonResult.error(SystemErrorCodeConstants.OAUTH2_UNKNOWN);
        }
    }

    /**
     * 刷新token
     * @param refreshToken
     * @return
     */
    public CommonResult<Map<String, Object>> refreshToken(String refreshToken) {
        if (StringUtils.isEmpty(refreshToken)) {
            return CommonResult.error(SystemErrorCodeConstants.OAUTH2_REFRESH_TOKEN_IS_NULL);
        }
        // 验证 refresh_token 是否有效
        OAuth2Authorization authorization = authorizationService.findByToken(refreshToken, OAuth2TokenType.REFRESH_TOKEN);
        if (authorization == null) {
            return CommonResult.error(SystemErrorCodeConstants.OAUTH2_REFRESH_TOKEN_NOT_FOUND);
        }
        UsernamePasswordAuthenticationToken o = (UsernamePasswordAuthenticationToken)authorization.getAttributes().get("java.security.Principal");
        AccAccountDO principal = (AccAccountDO)o.getPrincipal();
        Map<String, Object> map = new HashMap<>();
        map.put("userName",principal.getUsername());
        map.put("account",principal.getAccountNo());
        map.put("id",principal.getId());
        // 获取客户端和认证用户信息
        RegisteredClient registeredClient = registeredClientRepository.findByClientId("client");
        if (registeredClient == null) {
            return CommonResult.error(SystemErrorCodeConstants.OAUTH2_REFRESH_TOKEN_NOT_FOUND);
        }

        // 检查 refresh token 是否过期
        OAuth2RefreshToken currentRefreshToken = authorization.getRefreshToken().getToken();
        if (currentRefreshToken.getExpiresAt().isBefore(Instant.now())) {
            return CommonResult.error(SystemErrorCodeConstants.OAUTH2_REFRESH_TOKEN_NOT_FOUND);
        }

        // 创建新的 access token
        JwsHeader headers = JwtUtils.headers().build();
        JwtClaimsSet claims = JwtUtils.accessTokenClaims(
                registeredClient, null, authorization.getPrincipalName(), registeredClient.getScopes(),map).build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(headers, claims);
        NimbusJwtEncoder jwtEncoder = new NimbusJwtEncoder(jwkSource);
        Jwt jwtAccessToken = jwtEncoder.encode(jwtEncoderParameters);
        OAuth2AccessToken oauth2AccessToken = new OAuth2AccessToken(
                OAuth2AccessToken.TokenType.BEARER, jwtAccessToken.getTokenValue(), Instant.now(),
                Instant.now().plus(Duration.ofDays(7)), registeredClient.getScopes());
        // 生成新的 refresh token（可选）
        OAuth2RefreshToken newRefreshToken = new OAuth2RefreshToken(
                Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes()), Instant.now(),
                Instant.now().plus(Duration.ofDays(14)));

        // 更新授权信息
        authorization = OAuth2Authorization.from(authorization)
                .token(oauth2AccessToken)
                .refreshToken(newRefreshToken)
                .build();
        authorizationService.save(authorization);
        // 构建响应
        Map<String, Object> tokens = new HashMap<>();
        tokens.put("access_token", oauth2AccessToken.getTokenValue());
        tokens.put("token_type", oauth2AccessToken.getTokenType().getValue());
        tokens.put("expires_in", oauth2AccessToken.getExpiresAt().getEpochSecond() - Instant.now().getEpochSecond());
        tokens.put("refresh_token", newRefreshToken.getTokenValue());
        tokens.put("refresh_expires_in", newRefreshToken.getExpiresAt().getEpochSecond() - Instant.now().getEpochSecond());

        return CommonResult.success(tokens);
    }

    public CommonResult logout(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authorization = request.getHeader("Authorization");
        if (org.apache.commons.lang3.StringUtils.isBlank(authorization)){
            return CommonResult.success();
        }
        OAuth2TokenType bearerType = OAuth2TokenType.ACCESS_TOKEN;
        String bearer = authorization.replaceAll("Bearer ", "");
        OAuth2Authorization byToken = authorizationService.findByToken(bearer, bearerType);
        if (byToken == null){
            return CommonResult.success();
        }
        authorizationService.remove(byToken);
        return CommonResult.success();
    }
}
