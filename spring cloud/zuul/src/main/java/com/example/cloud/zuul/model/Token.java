package com.example.cloud.zuul.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Token implements Serializable {


  private static final long serialVersionUID = -6627499882030565108L;

  private String access_token;

  private String refresh_token;

  private String token_type;

  private String expires_in;

  private String scope;

  private String jti;

  //private String token;
}
