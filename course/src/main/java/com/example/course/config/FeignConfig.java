package com.example.course.config;

import com.example.course.interceptor.BasicAuthRequestInterceptor;
import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class FeignConfig {

  /**
   * 日志级别
   * @return
   */
  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

  /**
   * 创建Feign请求拦截器，在发送请求前设置认证的token,各个微服务将token设置到环境变量中来达到通用
   * @return
   */
  @Bean
  public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
    return new BasicAuthRequestInterceptor();
  }
}
