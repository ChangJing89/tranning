package com.example.auth.handler;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.example.common.utils.CallResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 处理用户未登录
 */
@Slf4j
@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {
  /**
   * 用户未登录返回结果
   * @Author Sans
   * @CreateTime 2019/10/3 9:01
   */
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception){
    try {
      response.getWriter().write(JSON.toJSONString(CallResult.failure("401","未登录")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}