package com.example.course.hystrix.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.example.course.hystrix.UserContextHolder;
import com.example.course.hystrix.model.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 拦截REST服务的每个调用，并从传入的http请求中检索此信息，
 * 然后将此上下文信息存储在自定义的UserContext对象中。在任何需要在REST服务调用中访问
 * 该值的时候，可以从ThreadLocal存储变量中检索UserContext并读取该值
 */
@Component
@Slf4j
public class UserContextFilter/* implements Filter*/ {


  /*@Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {


    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

    UserContextHolder.getContext().setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
    UserContextHolder.getContext().setUserId(httpServletRequest.getHeader(UserContext.USER_ID));
    UserContextHolder.getContext().setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));

    logger.info("UserContextFilter token: {}", UserContextHolder.getContext().getAuthToken());

    filterChain.doFilter(httpServletRequest, servletResponse);
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void destroy() {}*/
}
