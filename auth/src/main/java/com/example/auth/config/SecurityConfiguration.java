package com.example.auth.config;

import javax.servlet.http.HttpServletResponse;

import com.example.auth.handler.FailureLoginHandler;
import com.example.auth.handler.SuccessLoginHandler;
import com.example.auth.handler.UserAccessDeniedHandler;
import com.example.auth.handler.UserAuthenticationEntryPoint;
import com.example.auth.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(2)
/*@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true, jsr250Enabled = true)*/
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private SuccessLoginHandler successLoginHandler;

  @Autowired
  private FailureLoginHandler failureLoginHandler;

  @Autowired
  private UserAccessDeniedHandler userAccessDeniedHandler;

  @Autowired
  private MyUserDetailsService myUserDetailsService;

  /**
   * 密码加密
   * @return
   */
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
  }


  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * 在websecurityConfiguration不拦截oauth要开放的资源
   * @param http
   * @throws Exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/login/**","/oauth/**","/token/**","/callback/**").permitAll() // 不进行权限验证的请求或资源
        .anyRequest().authenticated() //其他请求需要登录后访问
        .and()
        .formLogin()
        .successHandler(successLoginHandler)// 登录成功后的处理动作
        .failureHandler(failureLoginHandler)
        .and()
        .exceptionHandling().authenticationEntryPoint((request, response, authException) -> response.sendError(
        HttpServletResponse.SC_UNAUTHORIZED))// 配置未登录处理类
        .accessDeniedHandler(userAccessDeniedHandler) //配置没有权限访问处理类
        .and()
        .csrf().disable();// 关闭 csrf 否则post;// 登陆失败后的处理动作;
  }
}
