package com.farzin.imdb.data.remote

import com.farzin.imdb.models.episode_guide.SeasonDetailModel
import com.farzin.imdb.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeGuideApiInterface {

    @GET("tv/{series_id}/season/{season_number}")
    suspend fun getSeasonDetails(
        @Path(
            value = "series_id",
            encoded = true
        ) seriesId:Int,
        @Path(
            value = "season_number",
            encoded = true
        ) seasonNumber:Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.USER_LANG
        ) : Response<SeasonDetailModel>

}