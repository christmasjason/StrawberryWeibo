package com.christmas.strawberryweibo.presenter.imp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.christmas.strawberryweibo.presenter.PictureActivityPresenter;
import com.christmas.strawberryweibo.view.PictureActivityView;

public class PictureActivityPresenterImp implements PictureActivityPresenter {

  private PictureActivityView pictureActivityView;

  public PictureActivityPresenterImp(PictureActivityView pictureActivityView) {
    this.pictureActivityView = pictureActivityView;
  }

  @Override
  public void savePictureLocal(@NonNull Context context, @NonNull Bitmap bitmap) {
    pictureActivityView.saveImageLocalSuccess("保存成功");
  }
}
