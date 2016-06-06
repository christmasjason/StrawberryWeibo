package com.christmas.strawberryweibo.presenter.imp;

import android.util.Log;

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
  public void startLoadPublicStatuses(String accessToken) {
    statusListModel.publicTimeLine(accessToken, 20, 1, this);
  }

  @Override
  public void onSuccess(Object response) {
    homeFragmentView.refreshPublicStatuses((StatusListWrapper) response);
  }

  @Override
  public void onError(String errorMessage) {

  }
}
