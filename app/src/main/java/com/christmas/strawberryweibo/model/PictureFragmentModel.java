package com.christmas.strawberryweibo.model;

import android.content.Context;
import android.support.annotation.NonNull;

public interface PictureFragmentModel {
  void loadImage(@NonNull Context context, @NonNull String url, @NonNull OnResponseListener onResponseListener);
}
