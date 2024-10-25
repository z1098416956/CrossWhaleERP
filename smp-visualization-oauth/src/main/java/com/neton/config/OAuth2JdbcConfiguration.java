package com.neton.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.oauth2.server.authorization.web.authentication.DelegatingAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2AuthorizationCodeAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2ClientCredentialsAuthenticationConverter;
import org.springframework.security.oauth2.server.authorization.web.authentication.OAuth2RefreshTokenAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.UUID;


/**
 * @author TheSunshine
 * @date 2024-10-24 15:42:00
 */
@EnableWebSecurity
@Configuration
public class OAuth2JdbcConfiguration {
    @Autowired
    private MD5PasswordEncoder passwordEncoder;
    @Resource
    private UserDetailsService userDetailService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CustomTokenEnhancer customTokenEnhancer;
    private static final String loginUrl = "/loginpage.html";

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        JdbcRegisteredClientRepository jdbcRegisteredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);

        return jdbcRegisteredClientRepository;

    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers( "/v1/oauth/create","/oauth/*","/*/*.css", "/*/*.ico", "/*/*.png", "/*/*.jpg", "/*/*.svg", "/login",
                        "/*/*.js", "/*/*.map",loginUrl, "/user/*","/base-grant.html").permitAll() // 允许所有用户访问这些路径
                .anyRequest().authenticated()
        );
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/v1/oauth/create","/login", "/logout", "/unlock/apply")); // 禁用CSRF保护
        // 表单登录
        http.formLogin(formlogin -> formlogin
                        .loginPage(loginUrl)
                        .loginProcessingUrl("/login"))
                .httpBasic(httpBasic -> {})
                .authenticationProvider(daoAuthenticationProvider());
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider customerDaoAuthenticationProvider = new DaoAuthenticationProvider();
        // 设置userDetailsService
        customerDaoAuthenticationProvider.setUserDetailsService(userDetailService);
        // 禁止隐藏用户未找到异常
        customerDaoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        // 使用MD5进行密码的加密
        customerDaoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return customerDaoAuthenticationProvider;
    }
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        //应用了默认的安全配置，这些配置支持OAuth2授权服务器的功能。
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                // 自定义用户名密码的授权方式
                .tokenEndpoint((tokenEndpoint) -> tokenEndpoint
                        .accessTokenRequestConverter(new DelegatingAuthenticationConverter(Arrays.asList(
                                new OAuth2AuthorizationCodeAuthenticationConverter(),
                                new OAuth2RefreshTokenAuthenticationConverter(),
                                new OAuth2ClientCredentialsAuthenticationConverter(),
                                new OAuth2PasswordAuthenticationConverter()   //添加密码模式的授权方式
                        ))).authenticationProviders((customProviders) -> {
                            // 自定义认证提供者
                            customProviders.add(new OAuth2PasswordAuthenticationProvider(jwkSource(), userDetailService, passwordEncoder));
                        })
                )
                //启用了OpenID Connect 1.0，这是一种基于OAuth2的身份验证协议。
                .oidc(Customizer.withDefaults());   // Enable OpenID Connect 1.0
        //配置了当用户尝试访问受保护资源但未认证时的行为。设置了一个自定义的登录页面作为认证入口点。
        http.exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(
                                new LoginUrlAuthenticationEntryPoint(loginUrl))
                )
                //配置了OAuth2资源服务器，指定使用JWT（JSON Web Token）进行身份验证。
                .oauth2ResourceServer(config -> config.jwt(Customizer.withDefaults()));
        return http.build();
    }
    @Bean
    public JwtEncoder jwtEncoder() {
        NimbusJwtEncoder jwtEncoder = new NimbusJwtEncoder(jwkSource());
        return jwtEncoder;
    }
    @Bean
    public JwtDecoder jwtDecoder() {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource());
    }
    @Bean
    public OAuth2TokenGenerator<?> tokenGenerator() {
        JwtGenerator jwtGenerator = new JwtGenerator(jwtEncoder());
        jwtGenerator.setJwtCustomizer(customTokenEnhancer);
        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(
                jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
    }
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }
    // 升版之后，采用RSA的方式加密token，与之前的版本有些差异，之前是采用HMAC加密
    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }
}
