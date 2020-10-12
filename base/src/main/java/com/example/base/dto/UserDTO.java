package com.example.base.dto;

import java.util.List;

import com.example.base.model.Role;
import com.example.common.utils.BaseDTO;
import lombok.Data;

@Data
public class UserDTO extends BaseDTO {

  private String id;

  private String name;

  private String mobile;

  private String password;

  private List<RoleDTO> roles;

}
