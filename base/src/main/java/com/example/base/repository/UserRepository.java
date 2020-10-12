package com.example.base.repository;

import java.util.List;

import com.example.base.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

  List<User> findAllByDeletedIsFalse();
}
