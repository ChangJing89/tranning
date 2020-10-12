package com.example.cloud.gateway.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@Configuration
@EnableOAuth2Sso
public class SecurityConfig {

  @Autowired
  private DataSource dataSource;

  @Autowired
  private AccessManager accessManager;



  @Bean
  SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception{
    //token管理器
   /* ReactiveAuthenticationManager tokenAuthenticationManager = new ReactiveJdbcAuthenticationManager(new JdbcTokenStore(dataSource));
    //认证过滤器
    AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(tokenAuthenticationManager);
    authenticationWebFilter.setServerAuthenticationConverter(new ServerBearerTokenAuthenticationConverter());*/

    http
        .httpBasic().disable()
        .csrf().disable()
        .authorizeExchange()
        .pathMatchers(HttpMethod.OPTIONS).permitAll()
        .anyExchange().access(accessManager)
 /*       .and()
        //oauth2认证过滤器
        .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)*/;
    return http.build();
  }

}
