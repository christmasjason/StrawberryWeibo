package com.christmas.strawberryweibo.presenter.imp;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.christmas.strawberryweibo.api.Oauthorize;
import com.christmas.strawberryweibo.infrastructure.RetrofitClient;
import com.christmas.strawberryweibo.infrastructure.Constant;
import com.christmas.strawberryweibo.model.Oauth2TokenModel;
import com.christmas.strawberryweibo.model.imp.Oauth2TokenModelImp;
import com.christmas.strawberryweibo.presenter.LoginActivityPresenter;
import com.christmas.strawberryweibo.view.LoginActivityView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivityPresenterImp implements
    LoginActivityPresenter {
  private LoginActivityView loginActivityView;
  private Oauth2TokenModel oauth2TokenModel;

  public LoginActivityPresenterImp(LoginActivityView loginActivityView) {
    this.loginActivityView = loginActivityView;
    oauth2TokenModel = new Oauth2TokenModelImp();
  }

  @Override
  public void handleRedirectedUrl(@NonNull Context context, @NonNull String url) {
    if (!url.contains("error")) {
      Uri uri = Uri.parse(url);
      String code = uri.getQueryParameter("code");
      RetrofitClient
          .retrofit
          .create(Oauthorize.class)
          .getAccessToken(Constant.APP_KEY, Constant.APP_SECRET, Constant.GRANT_TYPE, code, Constant.REDIRECT_URI)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(oauth2Token -> {
            oauth2TokenModel.saveAccessToken(oauth2Token);
            loginActivityView.setOauth2Token(oauth2Token);
          });

    } else {
      // TODO: 6/2/16 error 处理
    }
  }
}
