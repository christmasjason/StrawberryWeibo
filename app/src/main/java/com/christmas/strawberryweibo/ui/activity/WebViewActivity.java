package com.christmas.strawberryweibo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.christmas.strawberryweibo.R;
import com.christmas.strawberryweibo.infrastructure.Constants;
import com.christmas.strawberryweibo.model.entity.Oauth2Token;
import com.christmas.strawberryweibo.presenter.WebViewActivityPresenter;
import com.christmas.strawberryweibo.presenter.imp.WebViewActivityPresenterImp;
import com.christmas.strawberryweibo.utility.ToastUtility;
import com.christmas.strawberryweibo.view.WebViewActivityView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity implements WebViewActivityView {

  @Bind(R.id.wv_web_view) WebView wvWebView;

  private static final String ACTION_URL = "actionUrl";
  private static final String BLANK_URL = "about:blank";

  private WebViewActivityPresenter webViewActivityPresenter;

  private String actionUrl;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (!getIntents()) {
      finish();
      return;
    }

    setContentView(R.layout.layout_web_view);

    ButterKnife.bind(this);

    webViewActivityPresenter = new WebViewActivityPresenterImp(this);

    initWebView();
  }

  private boolean getIntents() {
    actionUrl = getIntent().getStringExtra(ACTION_URL);
    if (TextUtils.isEmpty(actionUrl)) {
      return false;
    }
    return true;
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
        if (url.startsWith(Constants.REDIRECT_URI)) {
          view.stopLoading();
          if (!hasCalledRedirectUrlHandler) {
            hasCalledRedirectUrlHandler = true;
            webViewActivityPresenter.handleRedirectedUrl(WebViewActivity.this, url);
          }
        } else {
          view.loadUrl(url);
        }
        return true;
      }

      @Override
      public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (!url.equals(BLANK_URL) && url.startsWith(Constants.REDIRECT_URI)) {
          view.stopLoading();
          if (!hasCalledRedirectUrlHandler) {
            hasCalledRedirectUrlHandler = true;
            webViewActivityPresenter.handleRedirectedUrl(WebViewActivity.this, url);
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
    ToastUtility.showLongToast(this, oauth2Token.toString());
  }

  @NonNull
  public static Intent newIntent(
      @NonNull Context context, @NonNull String actionUrl) {
    Intent intent = new Intent(context, WebViewActivity.class);
    intent.putExtra(ACTION_URL, actionUrl);
    return intent;
  }
}
