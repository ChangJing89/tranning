package com.example.teacher.service;

import com.example.teacher.model.Teacher;

public interface TeacherService {

  Teacher save (Teacher teacher);

  Teacher findTeacherById (String id);
}
