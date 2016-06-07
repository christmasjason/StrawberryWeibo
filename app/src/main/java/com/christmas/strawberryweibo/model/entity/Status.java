package com.christmas.strawberryweibo.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Status {
  @SerializedName("created_at") public String createdAt;
  @SerializedName("in_reply_to_status_id") public String inReplyToStatusId;
  @SerializedName("in_reply_to_user_id") public String inReplyToUserId;
  @SerializedName("in_reply_to_screen_name") public String inReplyToScreenName;
  @SerializedName("thumbnail_pic") public String thumbnailPic;
  @SerializedName("bmiddle_pic") public String middlePic;
  @SerializedName("original_pic") public String originalPic;
  @SerializedName("reposts_count") public int repostsCount;
  @SerializedName("comments_count") public int commentsCount;
  @SerializedName("source_allowclick") public int sourceAllowClick;
  @SerializedName("source_type") public int sourceType;
  @SerializedName("pic_urls") public List<Picture> picUrls;
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
