package com.example.course.service;

import com.example.course.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

@EnableBinding(Sink.class)
@Slf4j
public class ReceiveMessageService {

  @StreamListener(target = Sink.INPUT)
  public void receiveUserMessage(@Payload User user) {
     logger.info("course app 接收到用户消息"+user.toString());
  }
}
