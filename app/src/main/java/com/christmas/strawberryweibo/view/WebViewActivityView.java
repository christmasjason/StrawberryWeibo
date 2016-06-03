package com.christmas.strawberryweibo.view;

import com.christmas.strawberryweibo.model.entity.Oauth2Token;

public interface WebViewActivityView {
  void finishAndStartMainPage();
  void setOauth2Token(Oauth2Token oauth2Token);
}
