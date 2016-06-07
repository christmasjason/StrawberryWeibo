package com.christmas.strawberryweibo.presenter;

public interface HomeFragmentPresenter {
  void startLoadPublicStatuses(String accessToken);
  void startLoadFriendsStatuses(String accessToken);
}
