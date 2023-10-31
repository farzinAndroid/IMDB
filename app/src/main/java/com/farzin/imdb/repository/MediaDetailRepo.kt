package com.farzin.imdb.repository

import com.farzin.imdb.data.remote.BaseApiResponse
import com.farzin.imdb.data.remote.MediaDetailApiInterface
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.mediaDetail.RatedTVModel
import com.farzin.imdb.models.mediaDetail.TVDetailModel
import javax.inject.Inject

class MediaDetailRepo @Inject constructor(private val api: MediaDetailApiInterface) : BaseApiResponse() {


    suspend fun getTVDetails(seriesId:Int) : NetworkResult<TVDetailModel> =
        safeApiCall {
            api.getTVDetails(seriesId)
        }


    suspend fun getRatedTV() : NetworkResult<RatedTVModel> =
        safeApiCall {
            api.getRatedTV()
        }
}