package com.neton.service;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neton.constants.SecurityConstant;
import com.neton.mixin.module.OAuth2AuthorizationModule;
import com.neton.util.ObjectMapperUtils;
import com.neton.util.RedisUtils;
import lombok.Setter;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.http.converter.json.SpringHandlerInstantiator;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author TheSunshine
 * @date 2024-10-30 16:03:15
 */
public class RedisOAuth2AuthorizationService implements OAuth2AuthorizationService {

    private final RegisteredClientRepository clientRepository;

    @Setter
    private ObjectMapper objectMapper = ObjectMapperUtils.objectMapper();

    public RedisOAuth2AuthorizationService(RegisteredClientRepository clientRepository, AutowireCapableBeanFactory beanFactory) {
        this.clientRepository = clientRepository;
        objectMapper.registerModule(new OAuth2AuthorizationModule());
        ClassLoader classLoader = this.getClass().getClassLoader();
        List<Module> securityModules = SecurityJackson2Modules.getModules(classLoader);
        objectMapper.registerModules(securityModules);
        objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());
        objectMapper.setHandlerInstantiator(new SpringHandlerInstantiator(beanFactory));
    }

    @Override
    public void save(OAuth2Authorization authorization) {
        // 获取客户端信息
        final String clientId = authorization.getRegisteredClientId();
        RegisteredClient registeredClient = clientRepository.findById(clientId);
        Assert.notNull(registeredClient, "客户端信息不可为空");
        Assert.notNull(registeredClient.getTokenSettings(), "token配置信息不可为空");

        // 获取授权码、AccessToken、RefreshToken过期时间，默认5分钟
        Duration codeLiveTime = registeredClient.getTokenSettings().getAuthorizationCodeTimeToLive();
        Duration accessTokenLiveTime = registeredClient.getTokenSettings().getAccessTokenTimeToLive();
        Duration refreshTokenLiveTime = registeredClient.getTokenSettings().getRefreshTokenTimeToLive();
        Duration stateLiveTime = codeLiveTime;

        // 获取authorizationId
        final String authorizationId = authorization.getId();
        final String idToAuthorizationKey = SecurityConstant.getIdToAuthorizationKey(authorizationId);

        // write()是用objectMapper序列化，想以原样存储原样获取方便查看
        RedisUtils.setEx(idToAuthorizationKey, write(authorization), accessTokenLiveTime.getSeconds(), TimeUnit.SECONDS);

        //因为授权码、AccessToken、RefreshToken都是通过此service存储和获取，所以都需要存储
        Optional.ofNullable(authorization.getToken(OAuth2AuthorizationCode.class)).ifPresent(token -> {
            final String codeToAuthorizationKey = SecurityConstant.getCodeToAuthorization(token.getToken().getTokenValue());
            RedisUtils.setEx(codeToAuthorizationKey, authorizationId, codeLiveTime.getSeconds(), TimeUnit.SECONDS);
        });
        Optional.ofNullable(authorization.getAccessToken()).ifPresent(token -> {
            final String accessToAuthorization = SecurityConstant.getAccessToAuthorization(token.getToken().getTokenValue());
            RedisUtils.setEx(accessToAuthorization, authorizationId, accessTokenLiveTime.getSeconds(), TimeUnit.SECONDS);
        });
        Optional.ofNullable(authorization.getRefreshToken()).ifPresent(token -> {
            final String refreshToAuthorization = SecurityConstant.getRefreshToAuthorization(token.getToken().getTokenValue());
            RedisUtils.setEx(refreshToAuthorization, authorizationId, refreshTokenLiveTime.getSeconds(), TimeUnit.SECONDS);
        });

        // 点击授权按钮，会根据state查询authorization，所以state也要存起来
        Optional.ofNullable(authorization.getAttribute(OAuth2ParameterNames.STATE)).ifPresent(token -> {
            final String stateToAuthorizationKey = SecurityConstant.getStateToAuthorization((String) token);
            RedisUtils.setEx(stateToAuthorizationKey, authorizationId, stateLiveTime.getSeconds(), TimeUnit.SECONDS);
        });
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        List<String> keysToRemove = new ArrayList<>();
        keysToRemove.add(SecurityConstant.getIdToAuthorizationKey(authorization.getId()));
        RedisUtils.delete(keysToRemove);
    }

    @Override
    public OAuth2Authorization findById(String id) {
        return Optional.ofNullable(RedisUtils.get(SecurityConstant.getIdToAuthorizationKey(id))).map(this::parse).orElse(null);
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        Assert.notBlank(token, "token不能为空");
        if (tokenType == null) {
            return Optional.ofNullable(RedisUtils.get(SecurityConstant.getCodeToAuthorization(token)))
                    .or(() -> Optional.ofNullable(RedisUtils.get(SecurityConstant.getAccessToAuthorization(token))))
                    .or(() -> Optional.ofNullable(RedisUtils.get(SecurityConstant.getRefreshToAuthorization(token))))
                    .map(this::findById).orElse(null);
        } else if (OAuth2ParameterNames.STATE.equals(tokenType.getValue())) {
            return Optional.ofNullable(RedisUtils.get(SecurityConstant.getStateToAuthorization(token))).map(this::findById).orElse(null);
        } else if (OAuth2ParameterNames.CODE.equals(tokenType.getValue())) {
            return Optional.ofNullable(RedisUtils.get(SecurityConstant.getCodeToAuthorization(token))).map(this::findById).orElse(null);
        } else if (OAuth2TokenType.ACCESS_TOKEN.equals(tokenType)) {
            return Optional.ofNullable(RedisUtils.get(SecurityConstant.getAccessToAuthorization(token))).map(this::findById).orElse(null);
        } else if (OAuth2TokenType.REFRESH_TOKEN.equals(tokenType)) {
            return Optional.ofNullable(RedisUtils.get(SecurityConstant.getRefreshToAuthorization(token))).map(this::findById).orElse(null);
        }
        return null;
    }

    /**
     * 将OAuth2Authorization对象序列化成字符串
     *
     * @param data  OAuth2Authorization对象
     * @return OAuth2Authorization字符串
     */
    private String write(Object data) {
        try {
            return this.objectMapper.writeValueAsString(data);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }

    /**
     * 将redis里的OAuth2Authorization反序列化成OAuth2Authorization对象
     *
     * @param data  OAuth2Authorization序列化串
     * @return OAuth2Authorization
     */
    private OAuth2Authorization parse(String data) {
        try {
            return this.objectMapper.readValue(data, new TypeReference<>() {
            });
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }
}
