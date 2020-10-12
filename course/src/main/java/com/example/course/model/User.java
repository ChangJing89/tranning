package com.example.course.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User implements Serializable {

  private String id;

  /**
   * 用户名称
   */
  private String name;

  private String mobile;
}
