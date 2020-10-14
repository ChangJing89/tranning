package com.example.auth.config;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 由于授权服务器本身也提供对外接口所以也配置资源服务ResourceServerConfigurer
 */
@Configuration
@EnableResourceServer
@Order(6)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Autowired
  private JwtTokenStore jwtTokenStore;

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.tokenStore(jwtTokenStore);
  }

  /*
   * ResourceServerConfigurerAdapter 配置token验证的资源
   * */
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.requestMatchers().antMatchers("/api/**").and().authorizeRequests().antMatchers("/api/**").authenticated();
  }

}
