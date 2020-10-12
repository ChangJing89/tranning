package com.example.cloud.zuul.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:application.yml"})
@ConfigurationProperties(prefix = "path")
public class DataFilterConfig {


  private List<String> authPath;

  private List<String> userLoginPath;

  public List<String> getAuthPath() {
    return authPath;
  }

  public void setAuthPath(List<String> authPath) {
    this.authPath = authPath;
  }

  public List<String> getUserLoginPath() {
    return userLoginPath;
  }

  public void setUserLoginPath(List<String> userLoginPath) {
    this.userLoginPath = userLoginPath;
  }
}
