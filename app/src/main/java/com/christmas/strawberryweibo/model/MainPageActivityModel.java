package com.christmas.strawberryweibo.model;

public interface MainPageActivityModel {
  void getTokenInfo(String accessToken, OnResponseListener onResponseListener);
  void getUserInfo(String accessToken, String uid, OnResponseListener onResponseListener);
}
