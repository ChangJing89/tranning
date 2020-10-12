package com.example.cloud.zuul.filter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.cloud.zuul.config.DataFilterConfig;
import com.example.common.utils.JwtUtil;
import com.example.common.utils.PathUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class AuthFilter extends ZuulFilter {

  private final static String singKey = "auth";

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private DataFilterConfig dataFilterConfig;

  @Override
  public String filterType() {
    return FilterConstants.PRE_TYPE;
  }

  @Override
  public int filterOrder() {
    return 2;
  }

  /**
   * 满足什么条件需要过滤
   * @return
   */
  @Override
  public boolean shouldFilter() {
    //路径与配置的相匹配，则执行过滤
    RequestContext ctx = RequestContext.getCurrentContext();
    for (String pathPattern : dataFilterConfig.getAuthPath()) {
      logger.info("request uri:" +ctx.getRequest().getRequestURI());
      if (PathUtil.isPathMatch(pathPattern, ctx.getRequest().getRequestURI())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    if(request.getMethod().equals("OPTIONS")){  //预请求 放行
      return null;
    }
    String header =   request.getHeader("Authorization");
    if(StringUtils.isEmpty(header)) {
      logger.warn("Authorization is empty");
      // 令 zuul 过滤该请求，不对其进行路由
      ctx.setSendZuulResponse(false);
      // 设置返回的错误码
      ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
    }else {
   //验证token
      if(header.startsWith("Bearer ")) {
        logger.info("bearer token存在");
        ctx.setSendZuulResponse(true);
      }else {
        logger.info("bearer token不存在");
        ctx.setSendZuulResponse(false);
        // 设置返回的错误码
        ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
      }
    }
    return null;
  }
}
