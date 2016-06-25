package com.christmas.strawberryweibo.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

public interface LoginActivityPresenter {
  void handleRedirectedUrl(@NonNull Context context, @NonNull String url);
}
