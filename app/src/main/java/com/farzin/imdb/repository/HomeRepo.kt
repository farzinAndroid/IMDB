package com.farzin.imdb.repository

import com.farzin.imdb.data.remote.BaseApiResponse
import com.farzin.imdb.data.remote.HomeApiInterface
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.AddToWatchListRequest
import com.farzin.imdb.models.home.AddToWatchListResult
import com.farzin.imdb.models.home.NowPlayingModel
import com.farzin.imdb.models.home.PopularTVModel
import com.farzin.imdb.models.home.TVBasedOnNetwork
import com.farzin.imdb.models.home.TrendingMoviesForWeek
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import com.farzin.imdb.models.home.WatchListTV
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


    suspend fun getNowPlaying(): NetworkResult<NowPlayingModel> =
        safeApiCall {
            api.getNowPlaying()
        }


    suspend fun addToWatchList(watchListRequest: AddToWatchListRequest): NetworkResult<AddToWatchListResult> =
        safeApiCall {
            api.addToWatchList(watchListRequest)
        }

    suspend fun getWatchListTV(): NetworkResult<WatchListTV> =
        safeApiCall {
            api.getWatchListTV()
        }

}