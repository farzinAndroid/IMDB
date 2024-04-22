package com.farzin.imdb.repository

import com.farzin.imdb.data.remote.BaseApiResponse
import com.farzin.imdb.data.remote.HomeApiInterface
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.AddToWatchListRequest
import com.farzin.imdb.models.home.AddToWatchListResult
import com.farzin.imdb.models.home.Movie
import com.farzin.imdb.models.home.NowPlayingModel
import com.farzin.imdb.models.home.TVModel
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import com.farzin.imdb.models.home.WatchListTV
import javax.inject.Inject

class HomeRepo @Inject constructor(private val api: HomeApiInterface) : BaseApiResponse() {

    suspend fun getTVShowsForDay(): NetworkResult<TrendingTVShowsForDay> =
        safeApiCall {
            api.getTVShowsForDay()
        }


    suspend fun getPopularTV(): NetworkResult<TVModel> =
        safeApiCall {
            api.getPopularTV()
        }


    suspend fun getTrendingMoviesForWeek(): NetworkResult<Movie> =
        safeApiCall {
            api.getTrendingMoviesForWeek()
        }


    suspend fun getTVBasedOnNetwork(netWorkId:Int): NetworkResult<TVModel> =
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


    suspend fun getWatchListMovie(): NetworkResult<Movie> =
        safeApiCall {
            api.getWatchListMovie()
        }



    suspend fun getMoviesBasedOnGenre(genre:String): NetworkResult<Movie> =
        safeApiCall {
            api.getMoviesBasedOnGenre(genres = genre)
        }

}