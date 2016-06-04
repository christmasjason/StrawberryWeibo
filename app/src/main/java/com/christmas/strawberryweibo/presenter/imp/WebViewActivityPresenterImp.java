package com.christmas.strawberryweibo.presenter.imp;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.christmas.strawberryweibo.model.Oauth2TokenModel;
import com.christmas.strawberryweibo.model.OnResponseListener;
import com.christmas.strawberryweibo.model.entity.Oauth2Token;
import com.christmas.strawberryweibo.model.imp.Oauth2TokenModelImp;
import com.christmas.strawberryweibo.presenter.WebViewActivityPresenter;
import com.christmas.strawberryweibo.view.WebViewActivityView;

public class WebViewActivityPresenterImp implements
    WebViewActivityPresenter,
    OnResponseListener {
  private WebViewActivityView webViewActivityView;
  private Oauth2TokenModel oauth2AccessToken;

  public WebViewActivityPresenterImp(WebViewActivityView webViewActivityView) {
    this.webViewActivityView = webViewActivityView;
    oauth2AccessToken = new Oauth2TokenModelImp();
  }

  @Override
  public void handleRedirectedUrl(@NonNull Context context, @NonNull String url) {
    if (!url.contains("error")) {
      Uri uri = Uri.parse(url);

      String code = uri.getQueryParameter("code");

      oauth2AccessToken.getAccessToken(code, this);

    } else {
      // TODO: 6/2/16 error 处理
    }
  }

  @Override
  public void onSuccess(Object response) {
    webViewActivityView.setOauth2Token((Oauth2Token) response);
  }

  @Override
  public void onError(String errorMessage) {

  }
}
