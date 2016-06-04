package com.christmas.strawberryweibo.model.entity;

import com.google.gson.annotations.SerializedName;

public class User {
  @SerializedName("screen_name") public String screenName;
  @SerializedName("profile_image_url") public String profileImageUrl;
  @SerializedName("followers_count") public int followersCount;
  @SerializedName("friends_count") public int friendsCount;
  @SerializedName("statuses_count") public int statusesCount;
  @SerializedName("favourites_count") public int favouritesCount;
  @SerializedName("created_at") public String createdAt;
  @SerializedName("allow_all_act_msg") public boolean allowAllActMsg;
  @SerializedName("geo_enabled") public boolean geoEnabled;
  @SerializedName("allow_all_comment") public boolean allowAllComment;
  @SerializedName("avatar_large") public String avatarLarge;
  @SerializedName("verified_reason") public String verifiedReason;
  @SerializedName("follow_me") public boolean followMe;
  @SerializedName("online_status") public int onlineStatus;
  @SerializedName("bi_followers_count") public int biFollowersCount;
  public int id;
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
