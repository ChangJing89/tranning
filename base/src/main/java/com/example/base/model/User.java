package com.example.base.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

import com.example.common.utils.BaseModel;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "t_user")
@ToString
public class User extends BaseModel implements Serializable {

  @Id
  @GeneratedValue(generator = "user_id")
  @GenericGenerator(name = "user_id", strategy = "uuid")
  private String id;

  /**
   * 用户名称
   */
  @NotBlank(message = "用户名不能为空！")
  @Column(length = 50,unique = true)
  private String name;

  @NotBlank(message = "用户手机号不能为空！")
  @Column(length = 11,unique = true)
  private String mobile;

  @NotBlank(message = "用户密码不能为空！")
  private String password;

  /**
   * 用户角色列表
   */
  @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
  @JoinTable(name="t_user_role", //用来指定中间表的名称
      //用于指定本表在中间表的字段名称，以及中间表依赖的是本表的哪个字段
      joinColumns= {@JoinColumn(name="user_id",referencedColumnName="id")},
      //用于指定对方表在中间表的字段名称，以及中间表依赖的是它的哪个字段
      inverseJoinColumns= {@JoinColumn(name="role_id",referencedColumnName="id")})
  private List<Role> roles = Lists.newArrayList();

  /**
   * 删除标志位
   */
  private boolean deleted = false;

  @Version
  private Long version;

}
