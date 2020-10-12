package com.example.base.service.user;

import java.util.List;

import com.example.base.model.User;

public interface UserService {

  List<User> findAll ();

  User create(User user);

  User getUserById(String id);

  User update(User user);
}
