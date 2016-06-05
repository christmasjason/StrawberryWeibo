package com.christmas.strawberryweibo.presenter.imp;

import com.christmas.strawberryweibo.model.StatusListModel;
import com.christmas.strawberryweibo.model.imp.StatusListModelImp;
import com.christmas.strawberryweibo.presenter.HomeFragmentPresenter;
import com.christmas.strawberryweibo.view.HomeFragmentView;

public class HomeFragmentPresenterImp implements HomeFragmentPresenter {
  private HomeFragmentView homeFragmentView;
  private StatusListModel statusListModel;

  public HomeFragmentPresenterImp(HomeFragmentView homeFragmentView) {
    this.homeFragmentView = homeFragmentView;
    statusListModel = new StatusListModelImp();
  }
}
