package com.farzin.imdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.TrendingTVShowsForWeek
import com.farzin.imdb.repository.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo:HomeRepo) : ViewModel() {

    var trendingTVShowsForWeek = MutableStateFlow<NetworkResult<TrendingTVShowsForWeek>>(NetworkResult.Loading())


    fun getAllApiCallsForHome(){

        viewModelScope.launch {

            launch {
                trendingTVShowsForWeek.emit(repo.getTVShowsForWeek())
            }

        }

    }


}