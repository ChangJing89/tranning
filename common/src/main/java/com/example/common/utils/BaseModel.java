package com.example.common.utils;

import java.util.Date;

public abstract class BaseModel {

  private Date createTime;

  private Date updateTime;

  public void setCreateTime(Date createTime) {
    this.createTime = createTime == null? new Date(System.currentTimeMillis()):createTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime == null? new Date(System.currentTimeMillis()):updateTime;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }
}
