package com.christmas.strawberryweibo.model;

import com.christmas.strawberryweibo.model.entity.Oauth2Token;

public interface OnOauth2TokenListener {
  void onSuccess(Oauth2Token oauth2Token);
  void onError(String errorMessage);
}
