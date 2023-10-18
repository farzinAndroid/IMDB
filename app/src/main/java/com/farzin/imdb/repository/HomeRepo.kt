package com.farzin.imdb.repository

import com.farzin.imdb.data.remote.BaseApiResponse
import com.farzin.imdb.data.remote.HomeApiInterface
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.PopularTVModel
import com.farzin.imdb.models.home.TVBasedOnNetwork
import com.farzin.imdb.models.home.TVBasedOnNetworkResult
import com.farzin.imdb.models.home.TrendingMoviesForWeek
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import javax.inject.Inject

class HomeRepo @Inject constructor(private val api: HomeApiInterface) : BaseApiResponse() {

    suspend fun getTVShowsForDay(): NetworkResult<TrendingTVShowsForDay> =
        safeApiCall {
            api.getTVShowsForDay()
        }


    suspend fun getPopularTV(): NetworkResult<PopularTVModel> =
        safeApiCall {
            api.getPopularTV()
        }


    suspend fun getTrendingMoviesForWeek(): NetworkResult<TrendingMoviesForWeek> =
        safeApiCall {
            api.getTrendingMoviesForWeek()
        }


    suspend fun getTVBasedOnNetwork(netWorkId:Int): NetworkResult<TVBasedOnNetwork> =
        safeApiCall {
            api.getTVBasedOnNetwork(withNetworks = netWorkId)
        }

}