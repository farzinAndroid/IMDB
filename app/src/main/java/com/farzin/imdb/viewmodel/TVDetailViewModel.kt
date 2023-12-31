package com.farzin.imdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.TrendingTVShowsForDay
import com.farzin.imdb.models.movieDetail.VideosModel
import com.farzin.imdb.models.tvDetail.AddRating
import com.farzin.imdb.models.tvDetail.AddRatingModel
import com.farzin.imdb.models.tvDetail.CastAndCrewModelTV
import com.farzin.imdb.models.tvDetail.ImagesModel
import com.farzin.imdb.models.tvDetail.RatedTVModel
import com.farzin.imdb.models.tvDetail.TVDetailModel
import com.farzin.imdb.models.tvDetail.TVReviewModel
import com.farzin.imdb.repository.TVDetailRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TVDetailViewModel @Inject constructor(private val repo: TVDetailRepo) : ViewModel() {

    val tvDetails = MutableStateFlow<NetworkResult<TVDetailModel>>(NetworkResult.Loading())
    val ratedTV = MutableStateFlow<NetworkResult<RatedTVModel>>(NetworkResult.Loading())
    val addRating = MutableStateFlow<NetworkResult<AddRatingModel>>(NetworkResult.Loading())
    val castAndCrew = MutableStateFlow<NetworkResult<CastAndCrewModelTV>>(NetworkResult.Loading())
    val recommendedTVShows =
        MutableStateFlow<NetworkResult<TrendingTVShowsForDay>>(NetworkResult.Loading())
    val imagesForTV = MutableStateFlow<NetworkResult<ImagesModel>>(NetworkResult.Loading())
    val reviewsTV = MutableStateFlow<NetworkResult<TVReviewModel>>(NetworkResult.Loading())
    val videosTV = MutableStateFlow<NetworkResult<VideosModel>>(NetworkResult.Loading())


    fun getTVDetails(seriesId: Int) {
        viewModelScope.launch {
            tvDetails.emit(repo.getTVDetails(seriesId))
        }
    }


    fun addRating(seriesId: Int, rating: AddRating) {
        viewModelScope.launch {
            addRating.emit(repo.addRating(seriesId, rating))
        }
    }

    fun getRatedTV() {
        viewModelScope.launch {
            ratedTV.emit(repo.getRatedTV())
        }
    }


    fun getTVCastAndCrew(seriesId: Int) {
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


    fun getRecommendedTVShows(seriesId: Int) {
        viewModelScope.launch {
            recommendedTVShows.emit(repo.getRecommendedTVShows(seriesId))
        }
    }


    fun getImagesForTV(seriesId: Int) {
        viewModelScope.launch {
            imagesForTV.emit(repo.getImagesForTV(seriesId))
        }
    }


    fun getReviewsForTV(seriesId: Int, page: Int) {
        viewModelScope.launch {
            reviewsTV.emit(repo.getReviewsForTV(seriesId, page))
        }
    }

    fun getVideosForTV(seriesId: Int) {
        viewModelScope.launch {
            videosTV.emit(repo.getVideosForTV(seriesId))
        }
    }

    val youtubeVideos = videosTV.map { networkResult ->
        when (networkResult) {
            is NetworkResult.Success -> {
                val items = networkResult.data?.results
                val filteredItems = items?.filter { video -> video.site == "YouTube" }
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