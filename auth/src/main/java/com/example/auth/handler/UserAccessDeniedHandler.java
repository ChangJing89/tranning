package com.example.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.example.common.utils.CallResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * 没有权限的处理类
 */
@Component
public class UserAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                     AccessDeniedException e) throws IOException, ServletException {
    httpServletResponse.getWriter().write(JSON.toJSONString(CallResult.failure("403","无权限访问，请管理员授权")));
  }
}
