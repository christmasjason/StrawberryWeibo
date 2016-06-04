package com.christmas.strawberryweibo.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Status {
  @SerializedName("created_at") public String createdAt;
  @SerializedName("in_reply_to_status_id") public String inReplyToStatusId;
  @SerializedName("in_reply_to_user_id") public String inReplyToUserId;
  @SerializedName("in_reply_to_screen_name") public String inReplyToScreenName;
  @SerializedName("reposts_count") public int repostsCount;
  @SerializedName("comments_count") public int commentsCount;
  public long id;
  public boolean favorited;
  public boolean truncated;
  public String text;
  public String source;
  public String mid;
  public Object geo;
  public User user;
  public List<?> annotations;
}
