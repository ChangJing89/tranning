package com.example.course.repository;

import java.util.List;

import com.example.course.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course,String> {

  List<Course> findAllByTeacherIdAndDeletedIsFalse(String id);
}
