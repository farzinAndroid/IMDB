package com.farzin.imdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.castDetail.CastDetailModel
import com.farzin.imdb.models.castDetail.CombinedCreditsModel
import com.farzin.imdb.repository.CastDetailRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CastDetailViewModel @Inject constructor(private val repo: CastDetailRepo) : ViewModel() {

    val castDetail = MutableStateFlow<NetworkResult<CastDetailModel>>(NetworkResult.Loading())
    val combinedCredits = MutableStateFlow<NetworkResult<CombinedCreditsModel>>(NetworkResult.Loading())

    fun getCastDetail(castId: Int) {
        viewModelScope.launch {

            launch {
                castDetail.emit(repo.getCastDetail(castId))
            }

            launch {
                combinedCredits.emit(repo.getCombinedCredits(castId))
            }

        }
    }

    val castTVCredits = combinedCredits.map { networkResult ->
        when (networkResult) {
            is NetworkResult.Success -> {
                val items = networkResult.data?.cast
                val filteredItems = items?.filter { cast -> cast.media_type == "tv" }
                NetworkResult.Success("Success", filteredItems)
            }

            is NetworkResult.Error -> {
                NetworkResult.Error(message = "Error", data = null)
            }

            is NetworkResult.Loading -> {
                NetworkResult.Loading()
            }
        }
    }

    val castMovieCredits = combinedCredits.map { networkResult ->
        when (networkResult) {
            is NetworkResult.Success -> {
                val items = networkResult.data?.cast
                val filteredItems = items?.filter { cast -> cast.media_type == "movie" }
                NetworkResult.Success("Success", filteredItems)
            }

            is NetworkResult.Error -> {
                NetworkResult.Error(message = "Error", data = null)
            }

            is NetworkResult.Loading -> {
                NetworkResult.Loading()
            }
        }
    }



}