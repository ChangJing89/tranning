package com.example.base.controller;

import java.util.List;

import com.example.base.dto.UserDTO;
import com.example.base.model.User;
import com.example.base.service.SendMessageService;
import com.example.base.service.user.UserService;
import com.example.common.utils.CallResult;
import com.example.common.utils.ConvertToModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 */
@RestController
@RequestMapping(value = "/users")
@Slf4j
public class UserController extends ConvertToModel {

  @Autowired
  private UserService userService;

  @Autowired
  private SendMessageService sendMessageService;

  /**
   * 创建用户
   * @param userDTO
   * @return
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  public CallResult<UserDTO> create (@RequestBody UserDTO userDTO) {
     User user = this.userService.create(this.toModel(userDTO,User.class));
     return CallResult.success(this.toDTO(user,UserDTO.class));
  }

  /**
   * 查询所有用户
   * @return
   */
  @RequestMapping(value = "",method = RequestMethod.GET)
  public CallResult<List<UserDTO>> findAll() {
    List<User> users = this.userService.findAll();
    List<UserDTO> userDTOS = this.toListDTO(users,UserDTO.class);
    return CallResult.success(userDTOS);
  }

  /**
   * 更新用户信息
   * @param userDTO
   * @return
   */
  @RequestMapping(value = "",method = RequestMethod.PUT)
  public CallResult<UserDTO> update(@RequestBody UserDTO userDTO){
    User user = this.userService.getUserById(userDTO.getId());
    if(user == null) {
      return CallResult.failure("500","id:"+userDTO.getId()+"不存在");
    }else {
      this.map(userDTO,user);
      this.userService.update(user);
      // 更新用户成功发送用户信息
      logger.info("base服务发送用户信息");
      this.sendMessageService.sendUserMessage(user);
      return CallResult.success(this.toDTO(user,UserDTO.class));
    }
  }
}
