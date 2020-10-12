package com.example.course.hystrix.filter;

import java.io.IOException;

import com.example.course.hystrix.UserContextHolder;
import com.example.course.hystrix.model.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Slf4j
public class UserContextInterceptor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(
      HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
      throws IOException {

    HttpHeaders headers = request.getHeaders();
    headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
    headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());

    return execution.execute(request, body);
  }
}
