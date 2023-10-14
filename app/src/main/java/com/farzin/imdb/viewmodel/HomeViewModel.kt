package com.farzin.imdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.PopularTVModel
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import com.farzin.imdb.repository.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo:HomeRepo) : ViewModel() {

    var trendingTVShowsForDay = MutableStateFlow<NetworkResult<TrendingTVShowsForDay>>(NetworkResult.Loading())
    var popularTV = MutableStateFlow<NetworkResult<PopularTVModel>>(NetworkResult.Loading())


    fun getAllApiCallsForHome(){

        viewModelScope.launch {

            launch {
                trendingTVShowsForDay.emit(repo.getTVShowsForDay())
            }


            launch {
                popularTV.emit(repo.getPopularTV())
            }

        }

    }


}