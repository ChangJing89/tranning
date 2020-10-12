package com.example.teacher.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ModifiedTeacherSource {

  String OUTPUT = "teacher-out";

  @Output(ModifiedTeacherSource.OUTPUT)
  MessageChannel output();


}
