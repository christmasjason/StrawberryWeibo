package com.christmas.strawberryweibo.presenter.imp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.christmas.strawberryweibo.presenter.PictureFragmentPresenter;
import com.christmas.strawberryweibo.util.ImageLoadUtil;
import com.christmas.strawberryweibo.view.PictureFragmentView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class PictureFragmentPresenterImp implements PictureFragmentPresenter {

  private PictureFragmentView pictureFragmentView;

  public PictureFragmentPresenterImp(PictureFragmentView pictureFragmentView) {
    this.pictureFragmentView = pictureFragmentView;
  }

  @Override
  public void loadImage(@NonNull Context context, @NonNull String url) {
    ImageLoadUtil.loadImageFromString(context, url, new Target() {
      @Override
      public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        pictureFragmentView.pictureLoadSuccess(bitmap);
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
