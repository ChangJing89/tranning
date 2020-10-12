package com.example.course.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * SSO客户端
 */
@Configuration
@EnableResourceServer
public class ResourceServerSecurityConfig extends ResourceServerConfigurerAdapter {

  /**
   * 密码加密
   * @return
   */
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  protected JwtAccessTokenConverter jwtTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setSigningKey("auth");
    return converter;
  }

  @Bean
  public TokenStore jwtTokenStore() {
    return new JwtTokenStore(jwtTokenConverter());
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources
        .tokenStore(jwtTokenStore());
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .anyRequest().authenticated().and()
        .requestMatchers().antMatchers("/**")
        .and()
        .exceptionHandling()
        .authenticationEntryPoint((request, response, authException) -> response.sendError(
            HttpServletResponse.SC_UNAUTHORIZED));
  }
}
