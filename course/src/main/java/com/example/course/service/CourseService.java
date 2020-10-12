package com.example.course.service;

import com.example.course.model.Course;

public interface CourseService {

  Course save (Course course);

  Course findById (String id);
}
