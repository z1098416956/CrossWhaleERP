package com.cross.whale.mixin.module;

import com.cross.whale.mixin.*;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;

import java.io.Serial;

/**
 * @author TheSunshine
 * @date 2024-10-30 15:57:40
 */
public class OAuth2AuthorizationModule extends SimpleModule {

    @Serial
    private static final long serialVersionUID = 1243758528885179491L;

    public OAuth2AuthorizationModule() {
        super(OAuth2AuthorizationModule.class.getName(), new Version(1, 0, 0, null, null, null));
    }

    @Override
    public void setupModule(SetupContext context) {
        SecurityJackson2Modules.enableDefaultTyping(context.getOwner());

        context.setMixInAnnotations(OAuth2Authorization.class, OAuth2AuthorizationMixin.class);
        context.setMixInAnnotations(OAuth2AuthorizationCode.class, OAuth2AuthorizationCodeMixin.class);
        context.setMixInAnnotations(OAuth2Authorization.Token.class, TokenMixin.class);
        context.setMixInAnnotations(OAuth2AccessToken.class, OAuth2AccessTokenMixin.class);
        context.setMixInAnnotations(OAuth2RefreshToken.class, OAuth2RefreshTokenMixin.class);
        context.setMixInAnnotations(AuthorizationGrantType.class, AuthorizationGrantTypeMixin.class);
        context.setMixInAnnotations(OAuth2AccessToken.TokenType.class, TokenTypeMixin.class);
    }

}
