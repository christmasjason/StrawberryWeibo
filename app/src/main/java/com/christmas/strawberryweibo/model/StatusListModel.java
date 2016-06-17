package com.christmas.strawberryweibo.model;

import com.christmas.strawberryweibo.model.entity.StatusListWrapper;

public interface StatusListModel {
  void savePublicTimeLine();
  void saveFriendsTimeline(StatusListWrapper statusList);
}
