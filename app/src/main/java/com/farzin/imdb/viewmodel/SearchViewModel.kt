package com.farzin.imdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.movieDetail.MovieDetailModel
import com.farzin.imdb.repository.SearchRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repo:SearchRepo) : ViewModel() {

    val movieDetails = MutableStateFlow<NetworkResult<MovieDetailModel>>(NetworkResult.Loading())

    fun getMovieDetails(movieId:Int) {
        viewModelScope.launch {
            launch {

            }
        }
    }


}