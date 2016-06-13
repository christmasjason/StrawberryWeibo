package com.christmas.strawberryweibo.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public interface PictureActivityPresenter {
  void savePictureLocal(@NonNull Context context, @NonNull Bitmap bitmap);
}
