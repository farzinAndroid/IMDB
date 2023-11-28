package com.farzin.imdb.repository

import com.farzin.imdb.data.remote.BaseApiResponse
import com.farzin.imdb.data.remote.CastDetailApiInterface
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.castDetail.CastDetailModel
import com.farzin.imdb.models.castDetail.CombinedCreditsModel
import javax.inject.Inject

class CastDetailRepo @Inject constructor(private val api: CastDetailApiInterface) : BaseApiResponse() {


    suspend fun getCastDetail(castId: Int): NetworkResult<CastDetailModel> =
        safeApiCall {
            api.getCastDetail(castId)
        }


    suspend fun getCombinedCredits(castId: Int): NetworkResult<CombinedCreditsModel> =
        safeApiCall {
            api.getCombinedCredits(castId)
        }


}