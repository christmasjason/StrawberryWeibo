package com.christmas.strawberryweibo.model.entity;

import com.google.gson.annotations.SerializedName;

public class Oauth2Token {

  public static final String KEY_UID = "uid";
  public static final String KEY_ACCESS_TOKEN = "access_token";
  public static final String KEY_EXPIRES_IN = "expires_in";
  public static final String KEY_REFRESH_TOKEN = "refresh_token";
  public static final String KEY_PHONE_NUM = "phone_num";

  @SerializedName("access_token") public String accessToken;
  @SerializedName("expires_in") public long expiresIn;
  @SerializedName("uid") public String uid;
}
