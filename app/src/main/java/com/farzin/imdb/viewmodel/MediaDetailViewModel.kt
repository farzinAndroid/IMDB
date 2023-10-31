package com.farzin.imdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.mediaDetail.RatedTVModel
import com.farzin.imdb.models.mediaDetail.TVDetailModel
import com.farzin.imdb.repository.MediaDetailRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MediaDetailViewModel @Inject constructor(private val repo: MediaDetailRepo) : ViewModel() {

    val tvDetails = MutableStateFlow<NetworkResult<TVDetailModel>>(NetworkResult.Loading())
    val ratedTV = MutableStateFlow<NetworkResult<RatedTVModel>>(NetworkResult.Loading())


    fun getTVDetails(seriesId:Int){
        viewModelScope.launch {
            launch {
                tvDetails.emit(repo.getTVDetails(seriesId))
            }
        }
    }

    fun getRatedTV(){
        viewModelScope.launch {
            ratedTV.emit(repo.getRatedTV())
        }
    }

}