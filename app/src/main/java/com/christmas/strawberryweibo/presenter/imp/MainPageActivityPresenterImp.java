package com.christmas.strawberryweibo.presenter.imp;

import com.christmas.strawberryweibo.model.MainPageActivityModel;
import com.christmas.strawberryweibo.model.OnResponseListener;
import com.christmas.strawberryweibo.model.entity.Oauth2Token;
import com.christmas.strawberryweibo.model.entity.User;
import com.christmas.strawberryweibo.model.imp.MainPageActivityModelImp;
import com.christmas.strawberryweibo.presenter.MainPageActivityPresenter;
import com.christmas.strawberryweibo.view.MainPageActivityView;

public class MainPageActivityPresenterImp implements
    MainPageActivityPresenter,
    OnResponseListener {

  private MainPageActivityView mainPageActivityView;
  private MainPageActivityModel mainPageActivityModel;

  public MainPageActivityPresenterImp(MainPageActivityView mainPageActivityView) {
    this.mainPageActivityView = mainPageActivityView;
    this.mainPageActivityModel = new MainPageActivityModelImp();
  }

  @Override
  public void getTokenInfo(String accessToken) {
    mainPageActivityModel.getTokenInfo(accessToken, this);
  }

  @Override
  public void getUserInfo(String accessToken, String uid) {
    mainPageActivityModel.getUserInfo(accessToken, uid, this);
  }

  @Override
  public void onSuccess(Object response) {
    if (response instanceof Oauth2Token) {
      mainPageActivityView.updateUserTokenInfo((Oauth2Token) response);
    } else {
      mainPageActivityView.updateUserInfo((User) response);
    }
  }

  @Override
  public void onError(String errorMessage) {

  }
}
