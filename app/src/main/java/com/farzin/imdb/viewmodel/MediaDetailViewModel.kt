package com.farzin.imdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.mediaDetail.AddRating
import com.farzin.imdb.models.mediaDetail.AddRatingModel
import com.farzin.imdb.models.mediaDetail.CastAndCrewModel
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
    val addRating = MutableStateFlow<NetworkResult<AddRatingModel>>(NetworkResult.Loading())
    val castAndCrew = MutableStateFlow<NetworkResult<CastAndCrewModel>>(NetworkResult.Loading())


    fun getTVDetails(seriesId:Int){
        viewModelScope.launch {
            launch {
                tvDetails.emit(repo.getTVDetails(seriesId))
            }
        }
    }



    fun addRating(seriesId:Int,rating: AddRating){
        viewModelScope.launch {
            launch {
                addRating.emit(repo.addRating(seriesId, rating))
            }
        }
    }

    fun getRatedTV(){
        viewModelScope.launch {
            ratedTV.emit(repo.getRatedTV())
        }
    }


    fun getTVCastAndCrew(seriesId:Int){
        viewModelScope.launch {
            castAndCrew.emit(repo.getTVCastAndCrew(seriesId))
        }
    }

}