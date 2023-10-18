package com.farzin.imdb.data.remote

import com.farzin.imdb.models.home.PopularTVModel
import com.farzin.imdb.models.home.TVBasedOnNetwork
import com.farzin.imdb.models.home.TVBasedOnNetworkResult
import com.farzin.imdb.models.home.TrendingMoviesForWeek
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import com.farzin.imdb.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiInterface {


    @GET("trending/tv/day")
    suspend fun getTVShowsForDay(
        @Query("api_key") apiKey : String = Constants.API_KEY
    ) : Response<TrendingTVShowsForDay>


    @GET("tv/popular")
    suspend fun getPopularTV(
        @Query("api_key") apiKey : String = Constants.API_KEY,
    ) : Response<PopularTVModel>



    @GET("trending/movie/week")
    suspend fun getTrendingMoviesForWeek(
        @Query("api_key") apiKey : String = Constants.API_KEY,
    ) : Response<TrendingMoviesForWeek>



    @GET("discover/tv")
    suspend fun getTVBasedOnNetwork(
        @Query("api_key") apiKey : String = Constants.API_KEY,
        @Query("with_networks") withNetworks : Int
    ) : Response<TVBasedOnNetwork>



}