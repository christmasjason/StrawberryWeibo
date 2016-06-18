package com.christmas.strawberryweibo.presenter.imp;

import com.christmas.strawberryweibo.api.RetrofitClient;
import com.christmas.strawberryweibo.api.Status;
import com.christmas.strawberryweibo.model.StatusListModel;
import com.christmas.strawberryweibo.model.imp.StatusListModelImp;
import com.christmas.strawberryweibo.presenter.HomeFragmentPresenter;
import com.christmas.strawberryweibo.view.HomeFragmentView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeFragmentPresenterImp implements HomeFragmentPresenter {

  private HomeFragmentView homeFragmentView;
  private StatusListModel statusListModel;

  public HomeFragmentPresenterImp(HomeFragmentView homeFragmentView) {
    this.homeFragmentView = homeFragmentView;
    statusListModel = new StatusListModelImp();
  }

  @Override
  public void loadPublicStatuses(String accessToken, int page) {
    RetrofitClient
        .retrofit
        .create(Status.class)
        .publicTimeline(accessToken, 20, page)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(statusListWrapper -> {
        });
  }

  @Override
  public void loadFriendsStatuses(String accessToken, int page) {
    RetrofitClient
        .retrofit
        .create(Status.class)
        .friendsTimeline(accessToken)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(statusListWrapper -> {
          statusListModel.saveFriendsTimeline(statusListWrapper);
          homeFragmentView.updateStatuses(statusListWrapper);
        });
  }
}
