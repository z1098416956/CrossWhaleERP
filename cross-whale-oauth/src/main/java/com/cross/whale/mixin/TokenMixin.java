package com.cross.whale.mixin;

import com.fasterxml.jackson.annotation.*;
import org.springframework.security.oauth2.core.OAuth2Token;

import java.util.Map;

/**
 * @author TheSunshine
 * @date 2024-10-30 15:56:38
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TokenMixin {

    @JsonCreator
    TokenMixin(@JsonProperty("token") OAuth2Token token) {
    }
}
