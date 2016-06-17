package com.christmas.strawberryweibo.presenter.imp;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.christmas.strawberryweibo.api.Oauthorize;
import com.christmas.strawberryweibo.api.RetrofitClient;
import com.christmas.strawberryweibo.infrastructure.Constants;
import com.christmas.strawberryweibo.model.Oauth2TokenModel;
import com.christmas.strawberryweibo.model.imp.Oauth2TokenModelImp;
import com.christmas.strawberryweibo.presenter.WebViewActivityPresenter;
import com.christmas.strawberryweibo.view.WebViewActivityView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WebViewActivityPresenterImp implements
    WebViewActivityPresenter {
  private WebViewActivityView webViewActivityView;
  private Oauth2TokenModel oauth2TokenModel;

  public WebViewActivityPresenterImp(WebViewActivityView webViewActivityView) {
    this.webViewActivityView = webViewActivityView;
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
          .getAccessToken(Constants.APP_KEY, Constants.APP_SECRET, Constants.GRANT_TYPE, code, Constants.REDIRECT_URI)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(oauth2Token -> {
            oauth2TokenModel.saveAccessToken(oauth2Token);
            webViewActivityView.setOauth2Token(oauth2Token);
          });

    } else {
      // TODO: 6/2/16 error 处理
    }
  }
}
