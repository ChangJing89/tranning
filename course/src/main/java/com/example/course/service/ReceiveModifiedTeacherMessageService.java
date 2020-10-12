package com.example.course.service;

import java.util.List;

import com.example.course.model.Course;
import com.example.course.model.Teacher;
import com.example.course.repository.CourseRepository;
import com.example.course.stream.UpdateTeacherInfoSink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.util.CollectionUtils;

/**
 * 接收教师修改的消息
 */
@EnableBinding(UpdateTeacherInfoSink.class)
@Slf4j
public class ReceiveModifiedTeacherMessageService {

  @Autowired
  private CourseRepository courseRepository;

  @StreamListener(target = UpdateTeacherInfoSink.INPUT)
  public void receiveMessage(@Payload Teacher teacher) {
    String teacherName = teacher.getName();
    logger.info("received a modified teacher message, the teacher is " + teacherName);
    List<Course> courses = this.courseRepository.findAllByTeacherIdAndDeletedIsFalse(teacher.getId());
    if(!CollectionUtils.isEmpty(courses)) {
      courses.forEach(course -> {
        course.setTeacherName(teacherName);
      });
      this.courseRepository.saveAll(courses);
      logger.info("the teachers of courses are modified successfully");
    }
  }
}
