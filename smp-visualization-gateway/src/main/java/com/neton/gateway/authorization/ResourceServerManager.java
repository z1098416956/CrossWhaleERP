package com.neton.gateway.authorization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author TheSunshine
 * @date 2024-10-31 18:06:27
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ResourceServerManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        //预检请求放行
        if (request.getMethod() == HttpMethod.OPTIONS){
            log.info("预检已放行===============> :{}",request.getMethod());
            return Mono.just(new AuthorizationDecision(true));
        }
        return Mono.just(new AuthorizationDecision(true));
    }
}
