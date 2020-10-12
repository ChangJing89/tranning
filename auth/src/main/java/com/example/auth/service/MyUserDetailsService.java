package com.example.auth.service;

import java.util.List;

import com.example.auth.model.BaseUser;
import com.example.auth.repository.BaseUserRepository;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private BaseUserRepository baseUserRepository;

  /**
   * 授权的时候是对角色授权，而认证的时候基于资源，因为资源是不变的，而用户角色是会变的
   * @param username
   * @return
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    BaseUser user = this.baseUserRepository.findByUsername(username);
    if(user == null) {
      logger.error("用户："+username+"不存在！");
      throw new UsernameNotFoundException("用户："+username+"不存在！");
    }
    List<SimpleGrantedAuthority> authorities = Lists.newArrayList();
    List<String> roles = user.getRoles();
    if(CollectionUtils.isEmpty(roles)) {
      throw new RuntimeException("用户["+username+"]没有分配角色！");
    }
    roles.forEach(role -> {
      authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
    });
    logger.info("用户["+username+"]角色["+roles.toString()+"]登录成功!");
    return new User(user.getUsername(),user.getPassword(),authorities);
  }
}
