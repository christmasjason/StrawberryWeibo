package com.christmas.strawberryweibo.model.imp;

import com.christmas.strawberryweibo.api.Oauthorize;
import com.christmas.strawberryweibo.api.RetrofitClient;
import com.christmas.strawberryweibo.infrastructure.Constants;
import com.christmas.strawberryweibo.model.Oauth2TokenModel;
import com.christmas.strawberryweibo.model.OnOauth2TokenListener;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Oauth2TokenModelImp implements Oauth2TokenModel {
  @Override
  public void getAccessToken(String code, OnOauth2TokenListener onOauth2TokenListener) {
    RetrofitClient
        .retrofit
        .create(Oauthorize.class)
        .getAccessToken(Constants.APP_KEY, Constants.APP_SECRET, Constants.GRANT_TYPE, code, Constants.REDIRECT_URI)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(onOauth2TokenListener::onSuccess);
  }
}
