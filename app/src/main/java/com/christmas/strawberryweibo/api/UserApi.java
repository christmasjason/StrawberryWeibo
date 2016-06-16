package com.christmas.strawberryweibo.api;

import com.christmas.strawberryweibo.model.entity.User;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface UserApi {
  @GET("2/users/show.json")
  Observable<User> userShow(
      @Query("access_token") String accessToken, @Query("uid") String uid);
}
