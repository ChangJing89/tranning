package com.example.teacher.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

import com.example.common.utils.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
@Data
@Entity
@Table(name = "t_teacher")
public class Teacher extends BaseModel implements Serializable {

  @Id
  @GeneratedValue(generator = "teacher_id")
  @GenericGenerator(name = "teacher_id", strategy = "uuid")
  private String id;

  /**
   * 老师姓名
   */
  @NotBlank(message = "老师姓名！")
  @Column(length = 20)
  private String name;

  /**
   * 老师编号
   */
  @NotBlank(message = "老师编号不能为空！")
  @Column(length = 20,unique = true)
  private String number;

  /**
   * 老师生日
   */
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Timestamp birthday;

  /**
   * 删除标志位
   */
  private boolean deleted = false;

  @Version
  private Long version;
}
