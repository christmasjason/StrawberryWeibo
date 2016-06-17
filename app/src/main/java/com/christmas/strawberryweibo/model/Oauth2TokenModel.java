package com.christmas.strawberryweibo.model;

import com.christmas.strawberryweibo.model.entity.Oauth2Token;

public interface Oauth2TokenModel {
  void saveAccessToken(Oauth2Token oauth2Token);
}
