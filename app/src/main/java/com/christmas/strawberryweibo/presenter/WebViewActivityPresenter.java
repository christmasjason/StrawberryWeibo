package com.christmas.strawberryweibo.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

public interface WebViewActivityPresenter {
  void handleRedirectedUrl(@NonNull Context context, @NonNull String url);
}
