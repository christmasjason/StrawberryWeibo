package com.christmas.strawberryweibo.apis;

import com.christmas.strawberryweibo.models.Oauth2AccessToken;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface Oauthorize {
  @FormUrlEncoded
  @POST("oauth2/access_token")
  Observable<Oauth2AccessToken> getAccessToken(
      @Field("client_id") String clientId,
      @Field("client_secret") String clientSecret,
      @Field("grant_type") String grantType,
      @Field("code") String code,
      @Field("redirect_uri") String redirectUri);
}
