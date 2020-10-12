package com.example.course.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

import com.example.common.utils.BaseModel;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "t_course")
public class Course extends BaseModel implements Serializable {

  @Id
  @GeneratedValue(generator = "course_id")
  @GenericGenerator(name = "course_id", strategy = "uuid")
  private String id;

  /**
   * 课程名称
   */
  @NotBlank(message = "课程名不能为空！")
  @Column(length = 50,unique = true)
  private String name;

  /**
   * 课程编号
   */
  @NotBlank(message = "课程编号不能为空！")
  @Column(length = 20,unique = true)
  private String number;

  /**
   * 老师id
   */
  private String teacherId;

  /**
   * 老师名称
   */
  private String teacherName;

  /**
   * 说明
   */
  @Lob
  private String comment;

  /**
   * 删除标志位
   */
  private boolean deleted = false;

  @Version
  private Long version;
}
