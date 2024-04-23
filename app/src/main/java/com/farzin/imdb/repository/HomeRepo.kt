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


    suspend fun getPopularTV(page: Int = 1): NetworkResult<TVModel> =
        safeApiCall {
            api.getPopularTV(page = page)
        }


    suspend fun getTrendingMoviesForWeek(page: Int = 1): NetworkResult<Movie> =
        safeApiCall {
            api.getTrendingMoviesForWeek(page = page)
        }


    suspend fun getTVBasedOnNetwork(netWorkId: Int, page: Int = 1): NetworkResult<TVModel> =
        safeApiCall {
            api.getTVBasedOnNetwork(withNetworks = netWorkId, page = page)
        }


    suspend fun getNowPlaying(page: Int = 1): NetworkResult<NowPlayingModel> =
        safeApiCall {
            api.getNowPlaying(page = page)
        }


    suspend fun addToWatchList(watchListRequest: AddToWatchListRequest): NetworkResult<AddToWatchListResult> =
        safeApiCall {
            api.addToWatchList(watchListRequest)
        }

    suspend fun getWatchListTV(page: Int = 1): NetworkResult<WatchListTV> =
        safeApiCall {
            api.getWatchListTV(page = page)
        }


    suspend fun getWatchListMovie(page: Int = 1): NetworkResult<Movie> =
        safeApiCall {
            api.getWatchListMovie(page = page)
        }


    suspend fun getMoviesBasedOnGenre(genre: String, page: Int = 1): NetworkResult<Movie> =
        safeApiCall {
            api.getMoviesBasedOnGenre(genres = genre, page = page)
        }

}