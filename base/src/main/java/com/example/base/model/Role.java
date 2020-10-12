package com.example.base.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

import com.example.common.utils.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "t_role")
public class Role extends BaseModel implements Serializable {
  private static final long serialVersionUID = 4960614467958411455L;

  @Id
  @GeneratedValue(generator = "role_id")
  @GenericGenerator(name = "role_id", strategy = "uuid")
  private String id;

  @NotBlank(message = "角色的英文名称不能为空！")
  @Column(length = 20,nullable = false)
  private String enName;

  @NotBlank(message = "角色名称不能为空！")
  @Column(length = 20,nullable = false)
  private String name;

  @ManyToMany(mappedBy = "roles",cascade = {
      CascadeType.REFRESH, CascadeType.MERGE},fetch = FetchType.LAZY)
  @JsonIgnore
  private List<User> users = Lists.newArrayList();

  /**
   * 删除标志位
   */
  private boolean deleted = false;

  @Version
  private Long version;

}
