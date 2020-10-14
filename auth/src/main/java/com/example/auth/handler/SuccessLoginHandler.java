package com.example.auth.handler;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.example.auth.model.BaseUser;
import com.example.common.utils.CallResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 用户登录成功处理类
 */
@Component
@Slf4j
public class SuccessLoginHandler implements AuthenticationSuccessHandler {

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse,
                                      Authentication authentication)
      throws IOException, ServletException {
    logger.info("用户登录成功"+authentication.getPrincipal());
    HashMap<String, Object> principalMap = objectMapper.readValue(
        objectMapper.writeValueAsString(authentication.getDetails()),
        new TypeReference<HashMap<String, Object>>() {
        }
    );
 /*   List<String> setCookies = httpServletResponse.getHeaders("Set-Cookie").parallelStream().collect(
        Collectors.toList());
    if(CollectionUtils.isEmpty(setCookies)) {
      httpServletResponse.getWriter().write(JSON.toJSONString(CallResult.failure("401","Set-Cookie为空")));
    } else {
      String setCookie = setCookies.get(0);
      String cookie = setCookie.substring(0,setCookie.indexOf(";"));
      logger.info("cookie:"+cookie);
      HttpHeaders headers = new HttpHeaders();
      headers.set("Cookie",cookie);
    }*/
    String header = httpServletRequest.getHeader("Authorization");
    /*if (header == null || !header.startsWith("Basic ")) {
    throw new UnapprovedClientAuthenticationException("请求头中无client信息");
  }*/
    httpServletResponse.getWriter().write(JSON.toJSONString(CallResult.success("登录成功")));
  }
}
