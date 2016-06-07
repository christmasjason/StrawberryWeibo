package com.christmas.strawberryweibo.model;

public interface StatusListModel {
  void publicTimeLine(
      String accessToken, int count, int page,
      OnResponseListener onResponseListener);
  void publicTimeLine(
      String accessToken, int count, int page, int baseApp,
      OnResponseListener onResponseListener);
  void friendsTimeline(String accessToken, OnResponseListener onResponseListener);
}
