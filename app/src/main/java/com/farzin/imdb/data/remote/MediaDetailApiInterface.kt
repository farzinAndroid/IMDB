package com.farzin.imdb.data.remote

import com.farzin.imdb.models.home.TrendingTVShowsForDay
import com.farzin.imdb.models.mediaDetail.AddRating
import com.farzin.imdb.models.mediaDetail.AddRatingModel
import com.farzin.imdb.models.mediaDetail.CastAndCrewModel
import com.farzin.imdb.models.mediaDetail.ImagesTVModel
import com.farzin.imdb.models.mediaDetail.RatedTVModel
import com.farzin.imdb.models.mediaDetail.TVDetailModel
import com.farzin.imdb.models.mediaDetail.TVReviewModel
import com.farzin.imdb.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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
        ) accountId:Int = Constants.ACC_ID.toInt(),
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("session_id") sessionId: String = Constants.SESSION_ID,
    ):Response<RatedTVModel>


    @POST("tv/{series_id}/rating")
    suspend fun addRating(
        @Path(
            "series_id",
            encoded = false
        ) seriesId:Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("session_id") sessionId: String = Constants.SESSION_ID,
        @Body rating:AddRating
    ):Response<AddRatingModel>


    @GET("tv/{series_id}/aggregate_credits")
    suspend fun getTVCastAndCrew(
        @Path(
            "series_id",
            encoded = false
        ) seriesId:Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
    ):Response<CastAndCrewModel>



    @GET("tv/{series_id}/recommendations")
    suspend fun getRecommendedTVShows(
        @Path(
            "series_id",
            encoded = false
        ) seriesId:Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.USER_LANG,
    ):Response<TrendingTVShowsForDay>


    @GET("tv/{series_id}/images")
    suspend fun getImagesForTV(
        @Path(
            "series_id",
            encoded = false
        ) seriesId:Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
    ):Response<ImagesTVModel>


    @GET("tv/{series_id}/reviews")
    suspend fun getReviewsForTV(
        @Path(
            "series_id",
            encoded = false
        ) seriesId:Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
    ):Response<TVReviewModel>


}