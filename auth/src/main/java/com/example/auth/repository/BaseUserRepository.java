package com.example.auth.repository;

import com.example.auth.model.BaseUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseUserRepository extends CrudRepository<BaseUser, String> {

  BaseUser findByUsername(String userName);
}
