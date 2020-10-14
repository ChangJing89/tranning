package com.example.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.example.common.utils.CallResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 登录失败处理类
 */
@Component
@Slf4j
public class FailureLoginHandler implements AuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse,
                                      AuthenticationException e)
      throws IOException, ServletException {
    CallResult result = new CallResult();
    if (e instanceof UsernameNotFoundException){
      logger.info("【登录失败】"+e.getMessage());
      result = CallResult.failure("500","用户名不存在");
    }else if (e instanceof LockedException){
      logger.info("【登录失败】"+e.getMessage());
      result = CallResult.failure("500","用户被冻结");
    } else if (e instanceof BadCredentialsException){
      logger.info("【登录失败】"+e.getMessage());
      result = CallResult.failure("500","用户名密码不正确");
    } else {
      logger.info("【登录失败】"+e.getMessage());
      result = CallResult.failure("500","登录失败，用户账号异常");
    }
    httpServletResponse.getWriter().write(JSON.toJSONString(result));
  }

}
