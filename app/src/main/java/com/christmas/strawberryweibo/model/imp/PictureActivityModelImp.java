package com.christmas.strawberryweibo.model.imp;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.christmas.strawberryweibo.model.OnResponseListener;
import com.christmas.strawberryweibo.model.PictureActivityModel;

public class PictureActivityModelImp implements PictureActivityModel {
  @Override
  public void savePictureLocal(
      @NonNull Bitmap bitmap, @NonNull OnResponseListener onResponseListener) {
    // TODO: 6/13/16 save picture to local and notify.
    onResponseListener.onSuccess("成功保存");
  }
}
