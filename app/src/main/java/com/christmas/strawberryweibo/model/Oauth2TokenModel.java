package com.christmas.strawberryweibo.model;

public interface Oauth2TokenModel {
  void getAccessToken(String code, OnOauth2TokenListener onOauth2TokenListener);
}
