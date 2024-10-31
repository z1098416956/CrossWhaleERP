package com.neton.config;

import com.neton.util.JwtUtils;
import com.nimbusds.jose.jwk.source.JWKSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import java.security.Principal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Set;
import java.util.function.Supplier;
/**
 * @author TheSunshine
 * @date 2024-10-25 10:50:22
 */
public class OAuth2PasswordAuthenticationProvider implements AuthenticationProvider{

    private static final StringKeyGenerator DEFAULT_REFRESH_TOKEN_GENERATOR =
            new Base64StringKeyGenerator(Base64.getUrlEncoder().withoutPadding(), 96);
    private OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer = (context) -> {};
    private Supplier<String> refreshTokenGenerator = DEFAULT_REFRESH_TOKEN_GENERATOR::generateKey;
    private AuthorizationServerSettings authorizationServerSettings;
    public OAuth2PasswordAuthenticationProvider(JWKSource jwkSource, UserDetailsService userDetailService,
                                                MD5PasswordEncoder passwordEncoder) {
        this.jwkSource = jwkSource;
        this.userDetailService = userDetailService;
        this.passwordEncoder = passwordEncoder;
    }
    private final JWKSource jwkSource;
    private UserDetailsService userDetailService;
    private MD5PasswordEncoder passwordEncoder;
    public OAuth2PasswordAuthenticationProvider(JWKSource jwkSource){
        this.jwkSource = jwkSource;
    }
    public void setJwtCustomizer(OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer) {
        Assert.notNull(jwtCustomizer, "jwtCustomizer cannot be null");
        this.jwtCustomizer = jwtCustomizer;
    }
    public void setRefreshTokenGenerator(Supplier<String> refreshTokenGenerator) {
        Assert.notNull(refreshTokenGenerator, "refreshTokenGenerator cannot be null");
        this.refreshTokenGenerator = refreshTokenGenerator;
    }
    void setAuthorizationServerSettings(AuthorizationServerSettings authorizationServerSettings) {
        this.authorizationServerSettings = authorizationServerSettings;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OAuth2PasswordAuthenticationToken passwordAuthentication =
                (OAuth2PasswordAuthenticationToken) authentication;
        OAuth2ClientAuthenticationToken clientPrincipal =
                getAuthenticatedClientElseThrowInvalidClient(passwordAuthentication);
        RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();
        // 校验账户
        String username = passwordAuthentication.getUsername();
        if (StringUtils.isEmpty(username)){
            throw new OAuth2AuthenticationException("账户不能为空");
        }
        // 校验密码
        String password = passwordAuthentication.getPassword();
        if (StringUtils.isEmpty(password)){
            throw new OAuth2AuthenticationException("密码不能为空");
        }
        // 查询账户信息
        UserDetails userDetails = userDetailService.loadUserByUsername(username);
        if (userDetails ==null) {
            throw new OAuth2AuthenticationException("账户信息不存在，请联系管理员");
        }
        // 校验密码
        if (!passwordEncoder.encode(password).equals(userDetails.getPassword())) {
            throw new OAuth2AuthenticationException("密码不正确");
        }
        // 构造认证信息
        Authentication principal = new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
        //region 直接构造一个OAuth2Authorization对象，实际场景中，应该去数据库进行校验
        OAuth2Authorization authorization = OAuth2Authorization.withRegisteredClient(registeredClient)
                .principalName(principal.getName())
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                .attribute(Principal.class.getName(), principal)
                .attribute("scopes", registeredClient.getScopes() )
                .build();
        //endregion
        String issuer = this.authorizationServerSettings != null ? this.authorizationServerSettings.getIssuer() : null;
        Set<String> authorizedScopes = authorization.getAttribute("scopes");
        // 构造jwt token信息
        JwsHeader.Builder headersBuilder = JwtUtils.headers();
        headersBuilder.header("client-id", registeredClient.getClientId());
        headersBuilder.header("authorization-grant-type", passwordAuthentication.getGrantType().getValue());
        JwtClaimsSet.Builder claimsBuilder = JwtUtils.accessTokenClaims(registeredClient, issuer, authorization.getPrincipalName(), authorizedScopes,null);
        // @formatter:off
        JwtEncodingContext context = JwtEncodingContext.with(headersBuilder, claimsBuilder)
                .registeredClient(registeredClient)
                .principal(authorization.getAttribute(Principal.class.getName()))
                .authorization(authorization)
                .authorizedScopes(authorizedScopes)
                .tokenType(OAuth2TokenType.ACCESS_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrant(passwordAuthentication)
                .build();
        // @formatter:on
        jwtCustomizer.customize(context);
        JwsHeader headers = context.getJwsHeader().build();
        JwtClaimsSet claims = context.getClaims().build();
        JwtEncoderParameters params = JwtEncoderParameters.from(headers, claims);
        NimbusJwtEncoder jwtEncoder  = new NimbusJwtEncoder(this.jwkSource);
        Jwt jwtAccessToken = jwtEncoder.encode(params);
        //Jwt jwtAccessToken = null;
        // 生成token
        OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                jwtAccessToken.getTokenValue(), jwtAccessToken.getIssuedAt(),
                jwtAccessToken.getExpiresAt(), authorizedScopes);

        OAuth2RefreshToken refreshToken = new OAuth2RefreshToken(
                refreshTokenGenerator.get(), Instant.now(), Instant.now().plus(30, ChronoUnit.DAYS));
        return new OAuth2AccessTokenAuthenticationToken(
                registeredClient, clientPrincipal, accessToken,refreshToken);
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return OAuth2PasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
    private OAuth2ClientAuthenticationToken getAuthenticatedClientElseThrowInvalidClient(Authentication authentication) {
        OAuth2ClientAuthenticationToken clientPrincipal = null;
        if (OAuth2ClientAuthenticationToken.class.isAssignableFrom(authentication.getPrincipal().getClass())) {
            clientPrincipal = (OAuth2ClientAuthenticationToken) authentication.getPrincipal();
        }
        if (clientPrincipal != null && clientPrincipal.isAuthenticated()) {
            return clientPrincipal;
        }
        throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);
    }


}
