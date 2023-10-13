package com.farzin.imdb.data.remote

import com.farzin.imdb.models.home.TrendingTVShowsForWeek
import com.farzin.imdb.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiInterface {


    @GET("trending/tv/week")
    suspend fun getTVShowsForWeek(
        @Query("api_key") apiKey : String = Constants.API_KEY
    ) : Response<TrendingTVShowsForWeek>



}