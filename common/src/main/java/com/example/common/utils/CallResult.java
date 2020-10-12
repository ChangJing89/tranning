package com.example.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 调用接口的返回结果集
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallResult<T> {

  /**
   * 返回结果success or failure
   */
  public enum Result {
    success, failure
  }

  private Result result;

  /**
   * http:404,500,401,403.....
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String code;

  /**
   * 实体类
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T data;

  /**
   * 返回结果的信息，如果报错显示错误信息
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String message;

  private CallResult(Result result) {
    this.result = result;
  }

  private CallResult(Result result, T data) {
    this.result = result;
    this.data = data;
  }

  private CallResult(Result result, String message) {
    this.result = result;
    this.message = message;
  }

  private CallResult(Result result, String code, String message) {
    this.result = result;
    this.code = code;
    this.message = message;
  }


  /**
   * 返回结果集成功
   */
  public static <T> CallResult<T> success(T data) {
    return new CallResult<>(Result.success,data);
  }

  /**
   * 返回结果集成功
   */
  public static <T> CallResult<T> success(String message) {
    return new CallResult<>(Result.success,message);
  }

  /**
   * 返回成功
   */
  public static <T> CallResult<T> success() {
    return new CallResult<T>(Result.success);
  }

  /**
   * 返回失败
   */
  public static <T> CallResult<T> failure() {
    return new CallResult<T>(Result.failure);
  }

  /**
   * 返回错误信息的编号和信息
   * @param code
   * @param message
   * @param <T>
   * @return
   */
  public static <T> CallResult<T> failure(String code, String message) {
    return new CallResult<T>(Result.failure, code,message);
  }


}
