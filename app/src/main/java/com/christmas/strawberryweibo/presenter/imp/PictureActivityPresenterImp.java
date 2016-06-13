package com.christmas.strawberryweibo.presenter.imp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.christmas.strawberryweibo.model.OnResponseListener;
import com.christmas.strawberryweibo.model.PictureActivityModel;
import com.christmas.strawberryweibo.model.imp.PictureActivityModelImp;
import com.christmas.strawberryweibo.presenter.PictureActivityPresenter;
import com.christmas.strawberryweibo.view.PictureActivityView;

public class PictureActivityPresenterImp implements
    PictureActivityPresenter,
    OnResponseListener {
  private PictureActivityView pictureActivityView;
  private PictureActivityModel pictureActivityModel;

  public PictureActivityPresenterImp(PictureActivityView pictureActivityView) {
    this.pictureActivityView = pictureActivityView;
    this.pictureActivityModel = new PictureActivityModelImp();
  }

  @Override
  public void savePictureLocal(@NonNull Context context, @NonNull Bitmap bitmap) {
    pictureActivityModel.savePictureLocal(bitmap, this);
  }

  @Override
  public void onSuccess(Object response) {
    pictureActivityView.saveImageLocalSuccess((String) response);
  }

  @Override
  public void onError(String errorMessage) {

  }
}
