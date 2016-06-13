package com.christmas.strawberryweibo.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

public interface PictureFragmentPresenter {
  void loadImage(@NonNull Context context, @NonNull String url);
}
