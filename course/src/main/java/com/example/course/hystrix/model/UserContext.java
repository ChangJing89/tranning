package com.example.course.hystrix.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UserContext {

  public static final String CORRELATION_ID = "correlationId";
  public static final String AUTH_TOKEN     = "Authorization";
  public static final String USER_ID        = "userId";

  private String correlationId= new String();
  private String authToken= new String();
  private String userId = new String();


}
