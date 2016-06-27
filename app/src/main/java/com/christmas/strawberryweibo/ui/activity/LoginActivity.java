package com.christmas.strawberryweibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.christmas.strawberryweibo.R;
import com.christmas.strawberryweibo.infrastructure.BaseActivity;
import com.christmas.strawberryweibo.infrastructure.Constant;
import com.christmas.strawberryweibo.model.entity.Oauth2Token;
import com.christmas.strawberryweibo.presenter.LoginActivityPresenter;
import com.christmas.strawberryweibo.presenter.imp.LoginActivityPresenterImp;
import com.christmas.strawberryweibo.util.SharedPreferencesUtil;
import com.christmas.strawberryweibo.view.LoginActivityView;

import butterknife.Bind;

public class LoginActivity extends BaseActivity implements LoginActivityView {

  @Bind(R.id.wv_web_view) WebView wvWebView;

  private static final String ACTION_URL = "actionUrl";
  private static final String BLANK_URL = "about:blank";

  private LoginActivityPresenter webViewActivityPresenter;

  private String actionUrl;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getParams();

    webViewActivityPresenter = new LoginActivityPresenterImp(this);

    initWebView();
  }

  @Override
  public int getLayoutRes() {
    return R.layout.layout_login;
  }

  private void getParams() {
    actionUrl = getIntent().getStringExtra(ACTION_URL);
  }

  private void initWebView() {
    WebSettings webSettings = wvWebView.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webSettings.setSaveFormData(false);
    webSettings.setSavePassword(false);
    webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

    wvWebView.loadUrl(actionUrl);

    wvWebView.setWebViewClient(new WebViewClient() {
      boolean hasCalledRedirectUrlHandler = false;

      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith(Constant.REDIRECT_URI)) {
          view.stopLoading();
          if (!hasCalledRedirectUrlHandler) {
            hasCalledRedirectUrlHandler = true;
            webViewActivityPresenter.handleRedirectedUrl(LoginActivity.this, url);
          }
        } else {
          view.loadUrl(url);
        }
        return true;
      }

      @Override
      public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (!url.equals(BLANK_URL) && url.startsWith(Constant.REDIRECT_URI)) {
          view.stopLoading();
          if (!hasCalledRedirectUrlHandler) {
            hasCalledRedirectUrlHandler = true;
            webViewActivityPresenter.handleRedirectedUrl(LoginActivity.this, url);
          }
          return;
        }
        super.onPageStarted(view, url, favicon);
      }
    });
  }

  @Override
  public void onBackPressed() {
    if (wvWebView.canGoBack()) {
      wvWebView.goBack();
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public void finishAndStartMainPage() {

  }

  @Override
  public void setOauth2Token(Oauth2Token oauth2Token) {
    SharedPreferencesUtil.put(this, Oauth2Token.KEY_ACCESS_TOKEN, oauth2Token.accessToken);
    SharedPreferencesUtil.put(this, Oauth2Token.KEY_UID, oauth2Token.uid);
    startActivity(MainPageActivity.newIntent(this));
    finish();
  }

  @NonNull
  public static Intent newIntent(
      @NonNull Context context, @NonNull String actionUrl) {
    Intent intent = new Intent(context, LoginActivity.class);
    intent.putExtra(ACTION_URL, actionUrl);
    return intent;
  }
}
