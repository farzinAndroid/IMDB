package com.farzin.imdb.data.remote

import com.farzin.imdb.models.home.AddToWatchListRequest
import com.farzin.imdb.models.home.AddToWatchListResult
import com.farzin.imdb.models.home.NowPlayingModel
import com.farzin.imdb.models.home.PopularTVModel
import com.farzin.imdb.models.home.TVBasedOnNetwork
import com.farzin.imdb.models.home.TrendingMoviesForWeek
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import com.farzin.imdb.models.home.WatchListTV
import com.farzin.imdb.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApiInterface {


    @GET("trending/tv/day")
    suspend fun getTVShowsForDay(
        @Query("api_key") apiKey: String = Constants.API_KEY,
    ): Response<TrendingTVShowsForDay>


    @GET("tv/popular")
    suspend fun getPopularTV(
        @Query("api_key") apiKey: String = Constants.API_KEY,
    ): Response<PopularTVModel>


    @GET("trending/movie/week")
    suspend fun getTrendingMoviesForWeek(
        @Query("api_key") apiKey: String = Constants.API_KEY,
    ): Response<TrendingMoviesForWeek>


    @GET("discover/tv")
    suspend fun getTVBasedOnNetwork(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("with_networks") withNetworks: Int,
    ): Response<TVBasedOnNetwork>


    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String = Constants.API_KEY,
    ): Response<NowPlayingModel>


    @POST("account/{account_id}/watchlist")
    suspend fun addToWatchList(
        @Body watchListRequest: AddToWatchListRequest,
        @Path(
            value = "account_id",
            encoded = true
        ) accountId: Int = Constants.ACC_ID,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("session_id") sessionId: String = Constants.SESSION_ID,
    ): Response<AddToWatchListResult>


    @GET("account/{account_id}/watchlist/tv")
    suspend fun getWatchListTV(
        @Path(
            value = "account_id",
            encoded = true
        ) accountId: Int = Constants.ACC_ID,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("session_id") sessionId: String = Constants.SESSION_ID,
    ): Response<WatchListTV>


    @GET("account/{account_id}/watchlist/movies")
    suspend fun getWatchListMovie(
        @Path(
            value = "account_id",
            encoded = true
        ) accountId: Int = Constants.ACC_ID,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("session_id") sessionId: String = Constants.SESSION_ID,
    ): Response<TrendingMoviesForWeek>



    @GET("discover/movie")
    suspend fun getMoviesBasedOnGenre(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("with_genres") genres: String,
    ): Response<TrendingMoviesForWeek>


}