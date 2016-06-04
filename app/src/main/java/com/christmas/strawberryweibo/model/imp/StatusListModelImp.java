package com.christmas.strawberryweibo.model.imp;

import com.christmas.strawberryweibo.api.RetrofitClient;
import com.christmas.strawberryweibo.api.Status;
import com.christmas.strawberryweibo.model.OnResponseListener;
import com.christmas.strawberryweibo.model.StatusListModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class StatusListModelImp implements StatusListModel {
  @Override
  public void publicTimeLine(
      String accessToken, int count, int page,
      OnResponseListener onResponseListener) {
    RetrofitClient
        .retrofit
        .create(Status.class)
        .publicTimeline(accessToken, count, page)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(onResponseListener::onSuccess);
  }

  @Override
  public void publicTimeLine(
      String accessToken, int count, int page, int baseApp,
      OnResponseListener onResponseListener) {
    RetrofitClient
        .retrofit
        .create(Status.class)
        .publicTimeline(accessToken, count, page, baseApp)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(onResponseListener::onSuccess);
  }
}
