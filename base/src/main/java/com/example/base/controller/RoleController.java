package com.example.base.controller;

import com.example.base.dto.RoleDTO;
import com.example.base.model.Role;
import com.example.base.service.role.RoleService;
import com.example.common.utils.CallResult;
import com.example.common.utils.ConvertToModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController extends ConvertToModel {

  @Autowired
  private RoleService roleService;

  /**
   * 创建角色
   * @param roleDTO
   * @return
   */
  @RequestMapping(value = "/role", method = RequestMethod.POST)
  public CallResult<RoleDTO> create (@RequestBody RoleDTO roleDTO) {
    Role role = this.toModel(roleDTO,Role.class);
    this.roleService.create(role);
    return CallResult.success();
  }
}
