package com.christmas.strawberryweibo.presenter;

public interface HomeFragmentPresenter {
  void loadPublicStatuses(String accessToken, int page);
  void loadFriendsStatuses(String accessToken, int page);
}
