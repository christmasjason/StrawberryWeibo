package com.christmas.strawberryweibo.apis;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit tools.
 */
public class RetrofitUtility {
  public static final String BASE_URL = "https://api.weibo.com/";

  public static Retrofit retrofit =
      new Retrofit
          .Builder()
          .baseUrl(BASE_URL)
          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create())
          .build();
}
