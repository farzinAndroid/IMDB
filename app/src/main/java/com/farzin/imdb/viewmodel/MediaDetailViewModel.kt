package com.farzin.imdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import com.farzin.imdb.models.mediaDetail.AddRating
import com.farzin.imdb.models.mediaDetail.AddRatingModel
import com.farzin.imdb.models.mediaDetail.CastAndCrewModel
import com.farzin.imdb.models.mediaDetail.ImagesTVModel
import com.farzin.imdb.models.mediaDetail.RatedTVModel
import com.farzin.imdb.models.mediaDetail.TVDetailModel
import com.farzin.imdb.models.mediaDetail.TVReviewModel
import com.farzin.imdb.repository.MediaDetailRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MediaDetailViewModel @Inject constructor(private val repo: MediaDetailRepo) : ViewModel() {

    val tvDetails = MutableStateFlow<NetworkResult<TVDetailModel>>(NetworkResult.Loading())
    val ratedTV = MutableStateFlow<NetworkResult<RatedTVModel>>(NetworkResult.Loading())
    val addRating = MutableStateFlow<NetworkResult<AddRatingModel>>(NetworkResult.Loading())
    val castAndCrew = MutableStateFlow<NetworkResult<CastAndCrewModel>>(NetworkResult.Loading())
    val recommendedTVShows = MutableStateFlow<NetworkResult<TrendingTVShowsForDay>>(NetworkResult.Loading())
    val imagesForTV = MutableStateFlow<NetworkResult<ImagesTVModel>>(NetworkResult.Loading())
    val reviewsTV = MutableStateFlow<NetworkResult<TVReviewModel>>(NetworkResult.Loading())


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

    val directorsList = castAndCrew.map { networkResult ->
        when (networkResult) {
            is NetworkResult.Success -> {
                val items = networkResult.data?.crew
                val filteredItems = items?.filter { crew -> crew.department == "Directing" }
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

    val writersList = castAndCrew.map { networkResult ->
        when (networkResult) {
            is NetworkResult.Success -> {
                val items = networkResult.data?.crew
                val filteredItems = items?.filter { crew -> crew.department == "Writing" }
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




    fun getRecommendedTVShows(seriesId:Int){
        viewModelScope.launch {
            recommendedTVShows.emit(repo.getRecommendedTVShows(seriesId))
        }
    }


    fun getImagesForTV(seriesId:Int){
        viewModelScope.launch {
            imagesForTV.emit(repo.getImagesForTV(seriesId))
        }
    }


    fun getReviewsForTV(seriesId:Int,page:Int){
        viewModelScope.launch {
            reviewsTV.emit(repo.getReviewsForTV(seriesId,page))
        }
    }

}