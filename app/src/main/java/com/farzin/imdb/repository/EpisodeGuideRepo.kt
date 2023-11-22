package com.farzin.imdb.repository

import com.farzin.imdb.data.remote.BaseApiResponse
import com.farzin.imdb.data.remote.EpisodeGuideApiInterface
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.episode_guide.SeasonDetailModel
import javax.inject.Inject

class EpisodeGuideRepo @Inject constructor(private val api: EpisodeGuideApiInterface) : BaseApiResponse() {


    suspend fun getSeasonDetails(seriesId:Int,seasonNumber:Int) : NetworkResult<SeasonDetailModel> =
        safeApiCall {
            api.getSeasonDetails(seriesId, seasonNumber)
        }





}