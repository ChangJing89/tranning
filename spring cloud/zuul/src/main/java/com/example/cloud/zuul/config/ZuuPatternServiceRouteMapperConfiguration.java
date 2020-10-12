package com.example.cloud.zuul.config;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuuPatternServiceRouteMapperConfiguration {

  /**
   * 正则表达式匹配服务路由
   */
  @Bean
  public PatternServiceRouteMapper serviceRouteMapper() {
    return new PatternServiceRouteMapper(
        "(?<version>v.*$)","${name}");
  }
}
