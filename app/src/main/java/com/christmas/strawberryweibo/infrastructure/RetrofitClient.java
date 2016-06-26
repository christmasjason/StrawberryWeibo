package com.christmas.strawberryweibo.infrastructure;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit tools.
 */
public class RetrofitClient {
  public static final String BASE_URL = "https://api.weibo.com/";
  public static final String OAUTHORIZE_URL =
      RetrofitClient.BASE_URL
          + "oauth2/authorize"
          + "?client_id="
          + Constants.APP_KEY
          + "&redirect_uri="
          + Constants.REDIRECT_URI;

  public static Retrofit retrofit =
      new Retrofit
          .Builder()
          .baseUrl(BASE_URL)
          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create())
          .build();
}
