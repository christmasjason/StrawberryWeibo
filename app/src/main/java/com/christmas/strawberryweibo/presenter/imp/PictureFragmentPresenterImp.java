package com.christmas.strawberryweibo.presenter.imp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.christmas.strawberryweibo.model.OnResponseListener;
import com.christmas.strawberryweibo.model.PictureFragmentModel;
import com.christmas.strawberryweibo.model.imp.PictureFragmentModelImp;
import com.christmas.strawberryweibo.presenter.PictureFragmentPresenter;
import com.christmas.strawberryweibo.view.PictureFragmentView;

public class PictureFragmentPresenterImp implements
    PictureFragmentPresenter,
    OnResponseListener {
  private PictureFragmentView pictureFragmentView;
  private PictureFragmentModel pictureFragmentModel;

  public PictureFragmentPresenterImp(PictureFragmentView pictureFragmentView) {
    this.pictureFragmentView = pictureFragmentView;
    this.pictureFragmentModel = new PictureFragmentModelImp();
  }

  @Override
  public void loadImage(@NonNull Context context, @NonNull String url) {
    pictureFragmentModel.loadImage(context, url, this);
  }

  @Override
  public void onSuccess(Object response) {
    pictureFragmentView.pictureLoadSuccess((Bitmap) response);
  }

  @Override
  public void onError(String errorMessage) {

  }
}
