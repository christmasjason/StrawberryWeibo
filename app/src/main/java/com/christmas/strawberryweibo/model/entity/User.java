package com.christmas.strawberryweibo.model.entity;

import com.google.gson.annotations.SerializedName;

public class User {
  @SerializedName("followers_count") public int followersCount;
  @SerializedName("friends_count") public int friendsCount;
  @SerializedName("statuses_count") public int statusesCount;
  @SerializedName("favourites_count") public int favouritesCount;
  @SerializedName("online_status") public int onlineStatus;
  @SerializedName("bi_followers_count") public int biFollowersCount;
  @SerializedName("allow_all_act_msg") public boolean allowAllActMsg;
  @SerializedName("geo_enabled") public boolean geoEnabled;
  @SerializedName("allow_all_comment") public boolean allowAllComment;
  @SerializedName("follow_me") public boolean followMe;
  @SerializedName("screen_name") public String screenName;
  @SerializedName("profile_image_url") public String profileImageUrl;
  @SerializedName("created_at") public String createdAt;
  @SerializedName("avatar_large") public String avatarLarge;
  @SerializedName("verified_reason") public String verifiedReason;
  public long id;
  public boolean following;
  public boolean verified;
  public String name;
  public String province;
  public String city;
  public String location;
  public String description;
  public String url;
  public String domain;
  public String gender;
  public String remark;
}
