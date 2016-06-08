package com.christmas.strawberryweibo.presenter.imp;

import com.christmas.strawberryweibo.model.OnResponseListener;
import com.christmas.strawberryweibo.model.StatusListModel;
import com.christmas.strawberryweibo.model.entity.StatusListWrapper;
import com.christmas.strawberryweibo.model.imp.StatusListModelImp;
import com.christmas.strawberryweibo.presenter.HomeFragmentPresenter;
import com.christmas.strawberryweibo.view.HomeFragmentView;

public class HomeFragmentPresenterImp implements
    HomeFragmentPresenter,
    OnResponseListener {
  private HomeFragmentView homeFragmentView;
  private StatusListModel statusListModel;

  public HomeFragmentPresenterImp(HomeFragmentView homeFragmentView) {
    this.homeFragmentView = homeFragmentView;
    statusListModel = new StatusListModelImp();
  }

  @Override
  public void loadPublicStatuses(String accessToken, int page) {
    statusListModel.publicTimeLine(accessToken, 20, page, this);
  }

  @Override
  public void loadFriendsStatuses(String accessToken, int page) {
    statusListModel.friendsTimeline(accessToken, this);
  }

  @Override
  public void onSuccess(Object response) {
    homeFragmentView.updateStatuses((StatusListWrapper) response);
  }

  @Override
  public void onError(String errorMessage) {

  }
}
