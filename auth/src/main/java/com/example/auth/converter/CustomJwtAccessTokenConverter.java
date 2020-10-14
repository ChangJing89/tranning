package com.example.auth.converter;

import java.util.HashMap;

import com.example.auth.model.BaseUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

public class CustomJwtAccessTokenConverter /*extends JwtAccessTokenConverter*/ {

  private final static String BEARER_PREFIX = "bearer";

  //这个是token增强器，想让jwt token携带额外的信息在这里处理
  /*@Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    if (accessToken instanceof DefaultOAuth2AccessToken) {
      Object principal = authentication.getPrincipal();

//这个principal是当时登录后存到securiy的东东，一般是用户实体，自己debug一下就知道了
      if (principal instanceof BaseUser) {
        BaseUser user = (BaseUser) principal;
        HashMap<String, Object> map = new HashMap<>();

        //jwt默认已经自带用户名，无需再次加入
        map.put("username", user.getUsername());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
      }
    }
    return super.enhance(accessToken, authentication);
  }


  //主要是资源服务器解析时一定要有bearer这个头才认为是一个oauth请求，但不知道为啥指定jwt后这个头就不见了，特意加上去
  @Override
  protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    return BEARER_PREFIX + super.encode(accessToken, authentication);
  }*/


}
