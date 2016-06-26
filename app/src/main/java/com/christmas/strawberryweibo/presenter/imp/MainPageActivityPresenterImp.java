package com.christmas.strawberryweibo.presenter.imp;

import com.christmas.strawberryweibo.api.Oauthorize;
import com.christmas.strawberryweibo.infrastructure.RetrofitClient;
import com.christmas.strawberryweibo.api.UserApi;
import com.christmas.strawberryweibo.model.Oauth2TokenModel;
import com.christmas.strawberryweibo.model.UserInfoModel;
import com.christmas.strawberryweibo.model.imp.Oauth2TokenModelImp;
import com.christmas.strawberryweibo.model.imp.UserInfoModelImp;
import com.christmas.strawberryweibo.presenter.MainPageActivityPresenter;
import com.christmas.strawberryweibo.view.MainPageActivityView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPageActivityPresenterImp implements MainPageActivityPresenter {

  private MainPageActivityView mainPageActivityView;
  private Oauth2TokenModel oauth2TokenModel;
  private UserInfoModel userInfoModel;

  public MainPageActivityPresenterImp(MainPageActivityView mainPageActivityView) {
    this.mainPageActivityView = mainPageActivityView;
    this.oauth2TokenModel = new Oauth2TokenModelImp();
    this.userInfoModel = new UserInfoModelImp();
  }

  @Override
  public void getTokenInfo(String accessToken) {
    RetrofitClient
        .retrofit
        .create(Oauthorize.class)
        .getTokenInfo(accessToken)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(oauth2Token -> {
          oauth2TokenModel.saveAccessToken(oauth2Token);
          mainPageActivityView.updateUserTokenInfo(oauth2Token);
        });
  }

  @Override
  public void getUserInfo(String accessToken, String uid) {
    RetrofitClient
        .retrofit
        .create(UserApi.class)
        .userShow(accessToken, uid)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(user -> {
          userInfoModel.saveUserInfo(user);
          mainPageActivityView.updateUserInfo(user);
        });
  }
}
