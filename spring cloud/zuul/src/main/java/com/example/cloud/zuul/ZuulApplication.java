package com.example.cloud.zuul;

import com.example.cloud.zuul.filter.AuthFilter;
import com.example.cloud.zuul.filter.LogFilter;
import com.example.common.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


@SpringBootApplication()
@EnableZuulProxy
public class ZuulApplication {

  public static void main(String[] args) {
    SpringApplication.run(ZuulApplication.class, args);
  }

  @Bean
  public JwtUtil jwtUtil() {
    return new JwtUtil();
  }

  @Bean
  public LogFilter logFilter() {
    return new LogFilter();
  }

  @Bean
  public AuthFilter authFilter() {
    return new AuthFilter();
  }

}
