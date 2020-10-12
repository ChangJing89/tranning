package com.example.course.controller;


import com.example.common.utils.CallResult;
import com.example.course.client.CourseClient;
import com.example.course.model.Course;
import com.example.course.model.User;
import com.example.course.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CourseController {

  @Autowired
  private CourseClient courseClient;

  @Autowired
  private CourseService courseService;


  @RequestMapping(value = "/feign/{id}", method = RequestMethod.GET)
  public CallResult<User> test(@PathVariable(value = "id") String id) {
    CallResult result = this.courseClient.getUserById(id);
    return result;
  }

  /**
   * 增加课程
   * @param course
   * @return
   */
  @RequestMapping(value = "",method = RequestMethod.POST)
  public CallResult<Course> save (@RequestBody Course course){
    return CallResult.success(this.courseService.save(course));
  }

  /**
   * 查询课程
   * @param id
   * @return
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public CallResult<Course> getCourseById(@PathVariable(value = "id")String id) {
    return CallResult.success(this.courseService.findById(id));
  }


}
