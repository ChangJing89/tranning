package com.example.cloud.zuul.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplate restTemplate () {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    HttpClient client = HttpClientBuilder.create()
        .setRedirectStrategy(new LaxRedirectStrategy()) // 允许所有请求的重定向
        .build();
    factory.setHttpClient(client);
    factory.setConnectionRequestTimeout(15000);
    factory.setReadTimeout(5000);
    return new RestTemplate(factory);
  }
}
