package com.christmas.strawberryweibo.models;

import com.google.gson.annotations.SerializedName;

public class Oauth2AccessToken {

  public static final String KEY_UID = "uid";
  public static final String KEY_ACCESS_TOKEN = "access_token";
  public static final String KEY_EXPIRES_IN = "expires_in";
  public static final String KEY_REFRESH_TOKEN = "refresh_token";
  public static final String KEY_PHONE_NUM = "phone_num";

  @SerializedName("access_token")
  public String accessToken;
  @SerializedName("expires_in")
  public long expiresIn;
  @Deprecated
  @SerializedName("remind_in")
  public String remindIn;
  @SerializedName("uid")
  public String uid;

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public long getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }

  @Deprecated
  public String getRemindIn() {
    return remindIn;
  }

  @Deprecated
  public void setRemindIn(String remindIn) {
    this.remindIn = remindIn;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  @Override
  public String toString() {
    return "Oauth2AccessToken{" +
        "accessToken='" + accessToken + '\'' +
        ", expiresIn=" + expiresIn +
        ", remindIn='" + remindIn + '\'' +
        ", uid='" + uid + '\'' +
        '}';
  }
}
