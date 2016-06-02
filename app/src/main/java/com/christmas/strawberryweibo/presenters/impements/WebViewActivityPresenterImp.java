package com.christmas.strawberryweibo.presenters.impements;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.christmas.strawberryweibo.presenters.WebViewActivityPresenter;
import com.christmas.strawberryweibo.views.WebViewActivityView;

public class WebViewActivityPresenterImp implements WebViewActivityPresenter {
  private WebViewActivityView webViewActivityView;

  public WebViewActivityPresenterImp(WebViewActivityView webViewActivityView) {
    this.webViewActivityView = webViewActivityView;
  }

  @Override
  public void handleRedirectedUrl(@NonNull Context context, @NonNull String url) {
    if (!url.contains("error")) {
      Uri uri = Uri.parse(url);

      String code = uri.getQueryParameter("code");

      webViewActivityView.getAccessToken(code);

    } else {
      // TODO: 6/2/16 error 处理
    }
  }
}
