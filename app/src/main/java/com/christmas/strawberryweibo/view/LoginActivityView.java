package com.christmas.strawberryweibo.view;

import com.christmas.strawberryweibo.model.entity.Oauth2Token;

public interface LoginActivityView {
  void finishAndStartMainPage();
  void setOauth2Token(Oauth2Token oauth2Token);
}
