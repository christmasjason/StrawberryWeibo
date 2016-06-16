package com.christmas.strawberryweibo.api;

import com.christmas.strawberryweibo.model.entity.Oauth2Token;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface Oauthorize {
  @FormUrlEncoded
  @POST("oauth2/access_token")
  Observable<Oauth2Token> getAccessToken(
      @Field("client_id") String clientId,
      @Field("client_secret") String clientSecret,
      @Field("grant_type") String grantType,
      @Field("code") String code,
      @Field("redirect_uri") String redirectUri);

  @GET("oauth2/revokeoauth2")
  Observable<Boolean> revokeOauth2(@Query("access_token") String accessToken);

  @FormUrlEncoded
  @POST("oauth2/get_token_info")
  Observable<Oauth2Token> getTokenInfo(@Field("access_token") String accessToken);
}
