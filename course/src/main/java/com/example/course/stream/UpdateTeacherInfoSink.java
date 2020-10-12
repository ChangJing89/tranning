package com.example.course.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface UpdateTeacherInfoSink {

  String INPUT = "teacher-in";
  @Input(INPUT)
  SubscribableChannel input();
}
