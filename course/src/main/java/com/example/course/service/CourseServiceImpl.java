package com.example.course.service;

import java.util.Random;

import com.example.common.utils.CallResult;
import com.example.course.model.Course;
import com.example.course.repository.CourseRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

  @Autowired
  private CourseRepository courseRepository;

  /**
   * 保存课程
   * @param course
   * @return
   */
  @Override
  public Course save(Course course) {
    return this.courseRepository.save(course);
  }

  /**
   * 查询课程
   * @param id
   * @return
   */
  @Override
  @HystrixCommand(fallbackMethod = "hystrixFallback",
      threadPoolKey = "courseByIdThreadPool",
      commandKey = "findCourseById",
      commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000")})
  public Course findById(String id) {
    this.randomlyRunLong();
    return this.courseRepository.findById(id).orElse(null);
  }


  private Course hystrixFallback(String id) {
    Course course = new Course();
    course.setComment("hystrix调用函数，id:"+id+"getCourseById 不可用");
    return course;
  }

  private void randomlyRunLong() {
    Random rand = new Random();

    int randomNum = rand.nextInt((3 - 1) + 1) + 1;

    if (randomNum == 3) {
      sleep();
    }
  }

  private void sleep() {
    try {
      Thread.sleep(11000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
