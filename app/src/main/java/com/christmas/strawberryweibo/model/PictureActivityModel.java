package com.christmas.strawberryweibo.model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public interface PictureActivityModel {
  void savePictureLocal(@NonNull Bitmap bitmap, OnResponseListener onResponseListener);
}
