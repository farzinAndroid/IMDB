package com.farzin.imdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.StateHolder
import com.farzin.imdb.models.home.AddToWatchListRequest
import com.farzin.imdb.models.home.AddToWatchListResult
import com.farzin.imdb.models.home.NowPlayingModel
import com.farzin.imdb.models.home.PopularTVModel
import com.farzin.imdb.models.home.TVBasedOnNetwork
import com.farzin.imdb.models.home.TrendingMoviesForWeek
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import com.farzin.imdb.models.home.WatchListTV
import com.farzin.imdb.repository.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo:HomeRepo) : ViewModel() {

    var trendingTVShowsForDay = MutableStateFlow<NetworkResult<TrendingTVShowsForDay>>(NetworkResult.Loading())
    var popularTV = MutableStateFlow<NetworkResult<PopularTVModel>>(NetworkResult.Loading())
    var trendingMoviesForWeek = MutableStateFlow<NetworkResult<TrendingMoviesForWeek>>(NetworkResult.Loading())
    var tVBasedOnNetwork = MutableStateFlow<NetworkResult<TVBasedOnNetwork>>(NetworkResult.Loading())
    var nowPlaying = MutableStateFlow<NetworkResult<NowPlayingModel>>(NetworkResult.Loading())
    var addToWatchList = MutableStateFlow<NetworkResult<AddToWatchListResult>>(NetworkResult.Loading())
    var watchListTV = MutableStateFlow<NetworkResult<WatchListTV>>(NetworkResult.Loading())
    var movieBasedOnGenre = MutableStateFlow<NetworkResult<TrendingMoviesForWeek>>(NetworkResult.Loading())


    fun getAllApiCallsForHome(){

        viewModelScope.launch {

            launch {
                trendingTVShowsForDay.emit(repo.getTVShowsForDay())
            }


            launch {
                popularTV.emit(repo.getPopularTV())
            }


            launch {
                trendingMoviesForWeek.emit(repo.getTrendingMoviesForWeek())
            }


            launch {
                nowPlaying.emit(repo.getNowPlaying())
            }









        }

    }


    fun getWatchListTV(){
        viewModelScope.launch {
            launch {
                watchListTV.emit(repo.getWatchListTV())
            }
        }
    }
    fun getTvBasedOnNetwork(networkId:Int){
        viewModelScope.launch {

            launch {
                tVBasedOnNetwork.emit(repo.getTVBasedOnNetwork(networkId))
            }

        }
    }

    fun getMoviesBasedOnGenre(genre:String){
        viewModelScope.launch {
            launch {
                movieBasedOnGenre.emit(repo.getMoviesBasedOnGenre(genre))
            }

        }
    }


    fun addToWatchList(watchListRequest: AddToWatchListRequest){
        viewModelScope.launch {

            launch {
                addToWatchList.emit(repo.addToWatchList(watchListRequest))
            }

        }
    }


}