package com.christmas.strawberryweibo.model.imp;

import com.christmas.strawberryweibo.api.Oauthorize;
import com.christmas.strawberryweibo.api.RetrofitClient;
import com.christmas.strawberryweibo.api.UserApi;
import com.christmas.strawberryweibo.model.MainPageActivityModel;
import com.christmas.strawberryweibo.model.OnResponseListener;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPageActivityModelImp implements MainPageActivityModel {
  @Override
  public void getTokenInfo(String accessToken, OnResponseListener onResponseListener) {
    RetrofitClient
        .retrofit
        .create(Oauthorize.class)
        .getTokenInfo(accessToken)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(onResponseListener::onSuccess);
  }

  @Override
  public void getUserInfo(String accessToken, String uid, OnResponseListener onResponseListener) {
    RetrofitClient
        .retrofit
        .create(UserApi.class)
        .userShow(accessToken, uid)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(onResponseListener::onSuccess);
  }
}
