package com.example.auth.config;

import javax.sql.DataSource;

import com.example.auth.service.MyUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 认证服务器配置,在成功验证资源所有者并获得授权后向客户端发出访问令牌
 */
@Configuration
@EnableAuthorizationServer
@Slf4j
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  /**
   * grant_type是 password,需要配置AuthenticationManager
   */
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  private MyUserDetailsService myUserDetailsService;

  @Autowired
  private PasswordEncoder passwordEncoder;


  @Bean
  public JwtTokenStore jwtTokenStore() {
    return new JwtTokenStore(jwtAccessTokenConverter());
  }

  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
    jwtAccessTokenConverter.setSigningKey("auth");   //  Sets the JWT signing key
    return jwtAccessTokenConverter;
  }

  /**
   * 用来配置令牌端点(Token Endpoint)的安全约束
   * @param security
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) {
    // 允许表单认证
    security.allowFormAuthenticationForClients().tokenKeyAccess("permitAll()")
        .checkTokenAccess("permitAll()");
  }

  /**
   * 用来配置客户端详情服务,客户端详情信息在这里进行初始化,通过数据库来存储调取注册的应用程序的名称以及密钥
   * 定义了哪些客户端将注册到服务
   * @param clientDetailsServiceConfigurer
   * @throws Exception
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
    clientDetailsServiceConfigurer.jdbc(dataSource);
  }

  /**
   * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services),Token存储采用的是JWT
   * @param endpoints
   * @throws Exception
   */
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    // 存数据库
    endpoints
        .accessTokenConverter(jwtAccessTokenConverter())
        .tokenStore(jwtTokenStore())
        .allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST)
        .userDetailsService(myUserDetailsService) // spring 使用spring提供的默认验证管理器和用户详细信息服务
       /*.authenticationManager(authenticationManager)*/;
  }

}
