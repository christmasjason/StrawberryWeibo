package com.christmas.strawberryweibo.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatusListWrapper {
  @SerializedName("previous_cursor") public long previousCursor;
  @SerializedName("total_number") public long totalNumber;
  @SerializedName("next_cursor") public long nextCursor;

  public List<Status> statuses;
}
