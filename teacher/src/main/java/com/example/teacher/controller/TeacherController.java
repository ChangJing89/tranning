package com.example.teacher.controller;

import com.example.common.utils.CallResult;
import com.example.common.utils.ConvertToModel;
import com.example.teacher.model.Teacher;
import com.example.teacher.service.SendModifiedTeacherMessageService;
import com.example.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController extends ConvertToModel {

  @Autowired
  private TeacherService teacherService;

  @Autowired
  private SendModifiedTeacherMessageService sendModifiedTeacherMessageService;

  /**
   * 保存教师信息
   * @param teacher
   * @return
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  public CallResult<Teacher> save (@RequestBody Teacher teacher){
    return CallResult.success(this.teacherService.save(teacher));
  }

  /**
   * 修改教师信息
   * @param teacher
   * @return
   */
  @RequestMapping(value = "", method = RequestMethod.PUT)
  public CallResult<Teacher> update (@RequestBody Teacher teacher){
    Teacher t = this.teacherService.findTeacherById(teacher.getId());
    if (t == null) {
      return CallResult.failure("500","id:"+teacher.getId()+"不存在");
    }
    this.map(teacher,t);
    this.teacherService.save(t);
    this.sendModifiedTeacherMessageService.sendModifiedTeacherMessage(t);
    return CallResult.success(t);
  }

}
