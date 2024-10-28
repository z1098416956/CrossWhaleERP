package com.neton.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author TheSunshine
 * @date 2024-10-28 17:56:29
 */
@Component
@ConfigurationProperties(prefix = "spring.security")
public class SecurityProperties {
    private List<String> ignoredPaths;

    public List<String> getIgnoredPaths() {
        return ignoredPaths;
    }

    public void setIgnoredPaths(List<String> ignoredPaths) {
        this.ignoredPaths = ignoredPaths;
    }
}
