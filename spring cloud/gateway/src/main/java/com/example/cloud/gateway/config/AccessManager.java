package com.example.cloud.gateway.config;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 权限管理
 */
@Component
@Slf4j
public class AccessManager implements ReactiveAuthorizationManager<AuthorizationContext> {

  ConcurrentHashMap<String,Integer> concurrentHashMap = new ConcurrentHashMap<>();
  private Set<String> permitAll = concurrentHashMap.keySet();
  private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

  public AccessManager (){
    permitAll.add("/");
    permitAll.add("/error");
    permitAll.add("/favicon.ico");
    permitAll.add("/**/swagger-resources/**");
    permitAll.add("/webjars/**");
    permitAll.add("/doc.html");
    permitAll.add("/swagger-ui.html");
    permitAll.add("/**/oauth/**");
  }



  @Override
  public Mono<AuthorizationDecision> check(Mono<Authentication> mono,
                                           AuthorizationContext authorizationContext) {
    ServerWebExchange exchange = authorizationContext.getExchange();
    //请求资源
    String requestPath = exchange.getRequest().getURI().getPath();
    // 是否直接放行
    if (permitAll(requestPath)) {
      return Mono.just(new AuthorizationDecision(true));
    }

    return mono.map(auth -> {
      return new AuthorizationDecision(checkAuthorities(exchange, auth));
    }).defaultIfEmpty(new AuthorizationDecision(false));
  }

  /**
   * 校验是否属于静态资源
   * @param requestPath 请求路径
   * @return
   */
  private boolean permitAll(String requestPath) {
    return permitAll.stream()
        .filter(r -> antPathMatcher.match(r, requestPath)).findFirst().isPresent();
  }

  /**
   * 权限校验
   * @param exchange
   * @param auth
   * @return
   */
  private boolean checkAuthorities(ServerWebExchange exchange, Authentication auth) {
    if(auth instanceof OAuth2Authentication){
      OAuth2Authentication authentication = (OAuth2Authentication) auth;
      String clientId = authentication.getOAuth2Request().getClientId();
      logger.info("clientId is {}",clientId);
    }

    Object principal = auth.getPrincipal();
    logger.info("用户信息:{}",principal.toString());
    return true;
  }

}
