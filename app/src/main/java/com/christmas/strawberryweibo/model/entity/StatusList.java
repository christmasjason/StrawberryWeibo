package com.christmas.strawberryweibo.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatusList {
  @SerializedName("previous_cursor") public int previousCursor;
  @SerializedName("total_number") public int totalNumber;
  @SerializedName("next_cursor") public long nextCursor;

  public List<Status> statuses;
}
