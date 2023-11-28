package com.farzin.imdb.data.remote

import com.farzin.imdb.models.castDetail.CastDetailModel
import com.farzin.imdb.models.castDetail.CombinedCreditsModel
import com.farzin.imdb.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CastDetailApiInterface {

    @GET("person/{person_id}")
    suspend fun getCastDetail(
        @Path(
            value = "person_id",
            encoded = true
        ) personId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
    ): Response<CastDetailModel>


    @GET("person/{person_id}/combined_credits")
    suspend fun getCombinedCredits(
        @Path(
            value = "person_id",
            encoded = true
        ) personId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
    ): Response<CombinedCreditsModel>

}