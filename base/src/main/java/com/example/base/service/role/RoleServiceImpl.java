package com.example.base.service.role;

import com.example.base.model.Role;
import com.example.base.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  /**
   * 创建用户
   * @param role
   * @return
   */
  @Override
  public Role create(Role role) {
    return this.roleRepository.save(role);
  }
}
