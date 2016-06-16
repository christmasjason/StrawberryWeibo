package com.christmas.strawberryweibo.presenter;

public interface MainPageActivityPresenter {
  void getTokenInfo(String accessToken);
  void getUserInfo(String accessToken, String uid);
}
