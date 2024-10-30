package com.neton.mixin;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.Instant;
import java.util.Map;

/**
 * @author TheSunshine
 * @date 2024-10-30 15:56:14
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public abstract class OAuth2RefreshTokenMixin {

    @JsonCreator
    OAuth2RefreshTokenMixin(@JsonProperty("tokenValue") String tokenValue,
                            @JsonProperty("issuedAt") Instant issuedAt,
                            @JsonProperty("expiresAt") Instant expiresAt) {
    }
}
