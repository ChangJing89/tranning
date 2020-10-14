package com.example.auth.dto;

import java.util.List;

import com.example.common.utils.BaseDTO;
import lombok.Data;

@Data
public class BaseUserDTO extends BaseDTO {

  private String id;

  private String username;

  private String password;

  private List<String> roles;
}
