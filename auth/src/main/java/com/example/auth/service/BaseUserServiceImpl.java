package com.example.auth.service;

import com.example.auth.model.BaseUser;
import com.example.auth.repository.BaseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class BaseUserServiceImpl implements BaseUserService {

  @Autowired
  private BaseUserRepository baseUserRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * 创建账号
   * @param baseUser
   * @return
   */
  @Override
  public BaseUser create(BaseUser baseUser) {
    baseUser.setPassword(passwordEncoder.encode(baseUser.getPassword()));
  /*  List<Role> roles = baseUser.getRoles();
    if(CollectionUtils.isEmpty(roles)) {
      throw new RuntimeException("创建用户["+baseUser.getUsername()+"]没有分配角色!");
    }
    List<Role> roleList = Lists.newArrayList();
    // 查询用户角色
    roles.forEach(role -> {
      Role r = this.roleRepository.findById(role.getId()).orElse(null);
      if(r == null) {
        throw new RuntimeException("角色id["+role.getId()+"]不存在！");
      }
      roleList.add(r);
    });
    baseUser.setRoles(roleList);*/
    return this.baseUserRepository.save(baseUser);
  }
}
