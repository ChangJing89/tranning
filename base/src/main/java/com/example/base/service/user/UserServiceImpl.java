package com.example.base.service.user;

import java.util.List;
import java.util.stream.Collectors;

import com.example.base.model.Role;
import com.example.base.model.User;
import com.example.base.repository.RoleRepository;
import com.example.base.repository.UserRepository;
import com.example.base.service.SendMessageService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private RoleRepository roleRepository;

  /**
   * 查询所有用户
   */
  @Override
  public List<User> findAll() {
    return this.userRepository.findAllByDeletedIsFalse();
  }

  /**
   * 创建用户
   */
  @Override
  public User create(User user) {
    user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    if(CollectionUtils.isEmpty(user.getRoles())) {
      throw new RuntimeException("用户角色为空");
    }
    List<String> roleIds = user.getRoles().stream().map(role -> role.getId()).collect(Collectors.toList());
    List<Role> roleList = Lists.newArrayList(this.roleRepository.findAllById(roleIds));
    user.setRoles(roleList);
    return this.userRepository.save(user);
  }

  /**
   * 查找用户
   */
  @Override
  public User getUserById(String id) {
    return this.userRepository.findById(id).orElse(null);
  }

  /**
   * 更新用户
   * @param user
   * @return
   */
  @Override
  public User update(User user) {
    user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    this.userRepository.save(user);
    return user;
  }

}