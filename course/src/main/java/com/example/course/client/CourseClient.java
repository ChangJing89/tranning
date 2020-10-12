package com.example.course.client;

import com.example.common.utils.CallResult;
import com.example.course.config.FeignConfig;
import com.example.course.hystrix.CourseClientFallback;
import com.example.course.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * name = "base" 是调用服务的名称
 */
@FeignClient(name="base",configuration = FeignConfig.class,fallback = CourseClientFallback.class)
public interface CourseClient {

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  CallResult<User> getUserById(@PathVariable(name = "id")String id);
}
