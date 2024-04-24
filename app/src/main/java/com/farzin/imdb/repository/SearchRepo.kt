package com.farzin.imdb.repository

import com.farzin.imdb.data.remote.BaseApiResponse
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.data.remote.SearchApiInterface
import com.farzin.imdb.models.home.Movie
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import javax.inject.Inject

class SearchRepo @Inject constructor(private val api: SearchApiInterface) : BaseApiResponse() {


    suspend fun searchMovie(query: String,page:Int = 1) : NetworkResult<Movie> =
        safeApiCall {
            api.searchMovie(query = query, page = page)
        }



    suspend fun searchTV(query: String,page:Int = 1) : NetworkResult<TrendingTVShowsForDay> =
        safeApiCall {
            api.searchTV(query = query, page = page)
        }


}