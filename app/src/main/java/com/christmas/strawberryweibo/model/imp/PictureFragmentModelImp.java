package com.christmas.strawberryweibo.model.imp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.christmas.strawberryweibo.model.OnResponseListener;
import com.christmas.strawberryweibo.model.PictureFragmentModel;
import com.christmas.strawberryweibo.util.ImageLoadUtil;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class PictureFragmentModelImp implements PictureFragmentModel {
  @Override
  public void loadImage(
      @NonNull Context context, @NonNull String url, @NonNull OnResponseListener onResponseListener) {
    ImageLoadUtil.loadImageFromString(context, url, new Target() {
      @Override
      public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        onResponseListener.onSuccess(bitmap);
      }

      @Override
      public void onBitmapFailed(Drawable errorDrawable) {

      }

      @Override
      public void onPrepareLoad(Drawable placeHolderDrawable) {

      }
    });
  }
}
