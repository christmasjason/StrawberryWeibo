package com.christmas.strawberryweibo.view;

import com.christmas.strawberryweibo.model.entity.StatusListWrapper;

public interface HomeFragmentView {
  void updateStatuses(StatusListWrapper statusList);
  void emptyStatuses();
}
