package com.farzin.imdb.repository

import com.farzin.imdb.data.remote.BaseApiResponse
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.data.remote.SearchApiInterface
import com.farzin.imdb.models.home.TrendingMoviesForWeek
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import javax.inject.Inject

class SearchRepo @Inject constructor(private val api: SearchApiInterface) : BaseApiResponse() {


    suspend fun searchMovie(query: String) : NetworkResult<TrendingMoviesForWeek> =
        safeApiCall {
            api.searchMovie(query = query)
        }



    suspend fun searchTV(query: String) : NetworkResult<TrendingTVShowsForDay> =
        safeApiCall {
            api.searchTV(query = query)
        }


}