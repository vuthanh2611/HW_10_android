package com.harbourspace.unsplash.api

import com.harbourspace.unsplash.data.model.UnsplashItem
import com.harbourspace.unsplash.data.model.UnsplashSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val AUTHORIZATION_CLIENT_ID = "Client-ID"
private const val ACCESS_KEY = "ngak5Lv2ZsDvYfnAJjyMP0mnV23pWs5hcvOBXceV3Wc"

interface UnsplashApi {

  @Headers("Authorization: $AUTHORIZATION_CLIENT_ID $ACCESS_KEY")
  @GET("photos")
  fun fetchPhotos() : Call<List<UnsplashItem>>

  @Headers("Authorization: $AUTHORIZATION_CLIENT_ID $ACCESS_KEY")
  @GET("search/photos")
  fun searchPhotos(@Query(value = "query") keyword : String): Call<UnsplashSearch>
}