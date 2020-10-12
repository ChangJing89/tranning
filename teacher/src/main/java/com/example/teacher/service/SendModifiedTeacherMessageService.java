package com.example.teacher.service;

import com.example.teacher.model.Teacher;
import com.example.teacher.stream.ModifiedTeacherSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(ModifiedTeacherSource.class)
@Slf4j
public class SendModifiedTeacherMessageService {

  @Autowired
  private ModifiedTeacherSource modifiedTeacherSource;

  /**
   * 发送teacher信息
   */
  public void sendModifiedTeacherMessage(Teacher teacher) {
    this.modifiedTeacherSource.output().send(MessageBuilder.withPayload(teacher).build());
    logger.info("teacher {id:"+teacher.getId()+" name:"+teacher.getName()+"} modified teacher message is sent successfully");
  }
}
