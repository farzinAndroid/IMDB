package com.farzin.imdb.repository

import com.farzin.imdb.data.remote.BaseApiResponse
import com.farzin.imdb.data.remote.HomeApiInterface
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import javax.inject.Inject

class HomeRepo @Inject constructor(private val api:HomeApiInterface) : BaseApiResponse() {

suspend fun getTVShowsForDay() : NetworkResult<TrendingTVShowsForDay> =
    safeApiCall {
        api.getTVShowsForDay()
    }

}