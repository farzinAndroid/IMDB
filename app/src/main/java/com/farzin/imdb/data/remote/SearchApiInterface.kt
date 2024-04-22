package com.farzin.imdb.data.remote

import com.farzin.imdb.models.home.Movie
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import com.farzin.imdb.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiInterface {

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.USER_LANG,
        @Query("query") query: String
    ):Response<Movie>


    @GET("search/tv")
    suspend fun searchTV(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.USER_LANG,
        @Query("query") query: String
    ):Response<TrendingTVShowsForDay>

}