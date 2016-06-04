package com.christmas.strawberryweibo.model;

public interface OnResponseListener {
  void onSuccess(Object response);
  void onError(String errorMessage);
}
