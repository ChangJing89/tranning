package com.example.teacher.service;

import com.example.teacher.model.Teacher;
import com.example.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

  @Autowired
  private TeacherRepository teacherRepository;

  /**
   * 增加/修改老师
   * @param teacher
   * @return
   */
  @Override
  public Teacher save(Teacher teacher) {
    return this.teacherRepository.save(teacher);
  }

  /**
   * 查询老师
   * @param id
   * @return
   */
  @Override
  public Teacher findTeacherById(String id) {
    return this.teacherRepository.findById(id).orElse(null);
  }



}
