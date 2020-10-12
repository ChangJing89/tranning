package com.example.course.interceptor;



import javax.servlet.http.HttpServletRequest;

import com.example.course.hystrix.UserContextHolder;
import com.example.course.hystrix.model.UserContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
public class BasicAuthRequestInterceptor implements RequestInterceptor {


  @Override
  public void apply(RequestTemplate requestTemplate) {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    Authentication authentication = securityContext.getAuthentication();
    if(authentication != null & authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
      requestTemplate.header(HttpHeaders.AUTHORIZATION, String.format("%s %s","Bearer",details.getTokenValue()));
      logger.info("Authorization:"+requestTemplate.headers());
    }
    /*ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    logger.info("header:"+request.getHeader("Authorization"));
    //添加token
    requestTemplate.header(HttpHeaders.AUTHORIZATION, String.format("%s %s","Bearer",request.getHeader("Authorization")));*/
  }
}
