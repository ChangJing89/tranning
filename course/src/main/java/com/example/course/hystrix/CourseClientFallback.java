package com.example.course.hystrix;

import com.example.common.utils.CallResult;
import com.example.course.client.CourseClient;
import com.example.course.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CourseClientFallback implements CourseClient {


  @Override
  public CallResult<User> getUserById(String id) {
    return CallResult.failure("500","熔断器回调函数 id:"+id+" 调用getUserById请求异常");
  }
}
