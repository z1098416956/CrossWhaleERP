package com.cross.whale.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;
import java.util.Map;
/**
 * @author TheSunshine
 * @date 2024-10-25 10:51:26
 */
public class OAuth2PasswordAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {
    private static final long serialVersionUID = -559176897708927684L;
    private final String username;
    private final String password;
    public OAuth2PasswordAuthenticationToken(String username, String password, Authentication clientPrincipal, Map<String, Object> additionalParameters) {
        super(AuthorizationGrantType.PASSWORD, clientPrincipal, additionalParameters);
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }
}
