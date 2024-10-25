package com.neton.util;

import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;

/**
 * @author TheSunshine
 * @date 2024-10-25 10:53:27
 */
public final class JwtUtils {

    private JwtUtils() {
    }
    public static JwsHeader.Builder headers() {
        return JwsHeader.with(SignatureAlgorithm.RS256);
    }
    public static JwtClaimsSet.Builder accessTokenClaims(RegisteredClient registeredClient,
                                                         String issuer, String subject,
                                                         Set<String> authorizedScopes) {
        Instant issuedAt = Instant.now();
        Instant expiresAt = issuedAt
                .plus(registeredClient.getTokenSettings().getAccessTokenTimeToLive());
        /**
         * iss (issuer)：签发人/发行人
         * sub (subject)：主题
         * aud (audience)：用户
         * exp (expiration time)：过期时间
         * nbf (Not Before)：生效时间，在此之前是无效的
         * iat (Issued At)：签发时间
         * jti (JWT ID)：用于标识该 JWT
         */
        // @formatter:off
        JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet.builder();
        if (StringUtils.hasText(issuer)) {
            claimsBuilder.issuer(issuer);
        }
        claimsBuilder
                .subject(subject)
                .audience(Collections.singletonList(registeredClient.getClientId()))
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .notBefore(issuedAt);
        if (!CollectionUtils.isEmpty(authorizedScopes)) {
            claimsBuilder.claim(OAuth2ParameterNames.SCOPE, authorizedScopes);
            claimsBuilder.claim("wangcl", "aaa");
        }
        // @formatter:on
        return claimsBuilder;
    }
}
