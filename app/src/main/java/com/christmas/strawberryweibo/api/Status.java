package com.christmas.strawberryweibo.api;

import android.support.annotation.NonNull;

import com.christmas.strawberryweibo.model.entity.StatusList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * weibo statuses api.
 */
public interface Status {

  @GET("2/statuses/public_timeline.json")
  Observable<StatusList> publicTimeline(
      @NonNull @Query("access_token") String accessToken,
      @Query("count") int count,
      @Query("page") int page);

  @GET("2/statuses/public_timeline.json")
  Observable<StatusList> publicTimeline(
      @NonNull @Query("access_token") String accessToken,
      @Query("count") int count,
      @Query("page") int page,
      @Query("base_app") int baseApp);
}
