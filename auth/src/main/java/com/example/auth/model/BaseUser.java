package com.example.auth.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

import com.example.common.utils.BaseModel;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户账号管理
 */
@Data
@Entity
@Table(name = "t_base_user")
public class BaseUser extends BaseModel implements UserDetails, Serializable {

  private static final long serialVersionUID = -7544067921730149582L;

  @Id
  @GeneratedValue(generator = "base_user_id")
  @GenericGenerator(name = "base_user_id", strategy = "uuid")
  private String id;

  @NotBlank(message = "用户名不能为空！")
  @Column(length = 50,nullable = false,unique = true)
  private String username;

  @NotBlank(message = "密码不能为空！")
  @Column(nullable = false)
  private String password;

  private boolean isAccountNonExpired = true;

  private boolean isAccountNonLocked = true;

  private boolean isCredentialsNonExpired = true;

  private boolean isEnabled = true;
  /**
   * 用户角色列表
   */
  @ElementCollection(fetch =FetchType.EAGER)
  List<String> roles = Lists.newArrayList();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
        .collect(Collectors.toList());
  }

  @Version
  private Long version;
}
