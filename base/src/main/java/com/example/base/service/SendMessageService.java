package com.example.base.service;

import com.example.base.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

/**
 * 消息生产者
 */
@EnableBinding(Source.class)
@Slf4j
public class SendMessageService {

  @Autowired
  private Source source;

  /**
   * 发送user信息
   */
  public void sendUserMessage(User user) {
     this.source.output().send(MessageBuilder.withPayload(user).build());
     logger.info("base app 消息发送成功");
  }

}
