package com.christmas.strawberryweibo.view;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

public interface PictureFragmentView {
  void pictureLoadSuccess(@NonNull Bitmap bitmap);
}
