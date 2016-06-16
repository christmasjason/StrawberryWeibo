package com.christmas.strawberryweibo.view;

import com.christmas.strawberryweibo.model.entity.Oauth2Token;
import com.christmas.strawberryweibo.model.entity.User;

public interface MainPageActivityView {
  void updateUserTokenInfo(Oauth2Token oauth2Token);
  void updateUserInfo(User user);
}
