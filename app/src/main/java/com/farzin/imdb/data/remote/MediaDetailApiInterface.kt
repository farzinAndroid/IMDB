package com.farzin.imdb.data.remote

import com.farzin.imdb.models.mediaDetail.RatedTVModel
import com.farzin.imdb.models.mediaDetail.TVDetailModel
import com.farzin.imdb.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MediaDetailApiInterface {


    @GET("tv/{series_id}")
    suspend fun getTVDetails(
        @Path(
            "series_id",
            encoded = false
        ) seriesId:Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.USER_LANG,
    ):Response<TVDetailModel>



    @GET("account/{account_id}/rated/tv")
    suspend fun getRatedTV(
        @Path(
            "account_id",
            encoded = false
        ) seriesId:Int = Constants.ACC_ID.toInt(),
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("session_id") sessionId: String = Constants.SESSION_ID,
    ):Response<RatedTVModel>

}