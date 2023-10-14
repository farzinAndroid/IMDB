package com.farzin.imdb.data.remote

import com.farzin.imdb.models.profile.RequestToken
import com.farzin.imdb.models.profile.SessionId
import com.farzin.imdb.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProfileApiInterface {


    @GET("authentication/token/new")
    suspend fun getInitialRequestToken(
        @Query("api_key") apiKey : String = Constants.API_KEY
    ) : Response<RequestToken>


    @GET("authentication/token/validate_with_login")
    suspend fun getLoginRequestToken(
        @Query("api_key") apiKey : String = Constants.API_KEY,
        @Query("username") username : String,
        @Query("password") password : String,
        @Query("request_token") requestToken : String,
    ) : Response<RequestToken>


    @POST("authentication/session/new")
    suspend fun getSessionId(
        @Query("api_key") apiKey : String = Constants.API_KEY,
        @Query("request_token") requestToken : String,
    ) : Response<SessionId>

}