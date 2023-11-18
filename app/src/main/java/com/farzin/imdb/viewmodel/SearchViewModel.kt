package com.farzin.imdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.TrendingMoviesForWeek
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import com.farzin.imdb.repository.SearchRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repo:SearchRepo) : ViewModel() {

    val searchedMovies = MutableStateFlow<NetworkResult<TrendingMoviesForWeek>>(NetworkResult.Loading())
    val searchedTV = MutableStateFlow<NetworkResult<TrendingTVShowsForDay>>(NetworkResult.Loading())

    fun searchMovie(query:String) {
        viewModelScope.launch {
            launch {
                searchedMovies.emit(repo.searchMovie(query))
            }
        }
    }


    fun searchTV(query:String) {
        viewModelScope.launch {
            launch {
                searchedTV.emit(repo.searchTV(query))
            }
        }
    }


}