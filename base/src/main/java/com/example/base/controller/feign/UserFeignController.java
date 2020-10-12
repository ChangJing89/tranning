package com.example.base.controller.feign;

import com.example.base.model.User;
import com.example.base.service.user.UserService;
import com.example.common.utils.CallResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserFeignController {

  @Autowired
  private UserService userService;

  /**
   * 查找用户
   * @param id
   * @return
   */
  @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
  CallResult<User> getUserById(@PathVariable(name = "id") String id){
    User user = this.userService.getUserById(id);
    return CallResult.success(user);
  }
}
