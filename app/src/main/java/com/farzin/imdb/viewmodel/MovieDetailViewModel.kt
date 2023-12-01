package com.farzin.imdb.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.TrendingMoviesForWeek
import com.farzin.imdb.models.movieDetail.MovieCastAndCrewModel
import com.farzin.imdb.models.movieDetail.MovieDetailModel
import com.farzin.imdb.models.movieDetail.RatedMovieModel
import com.farzin.imdb.models.movieDetail.VideosModel
import com.farzin.imdb.models.tvDetail.AddRating
import com.farzin.imdb.models.tvDetail.AddRatingModel
import com.farzin.imdb.models.tvDetail.ImagesTVModel
import com.farzin.imdb.models.tvDetail.TVReviewModel
import com.farzin.imdb.repository.MovieDetailRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repo: MovieDetailRepo) : ViewModel() {

    val movieDetails = MutableStateFlow<NetworkResult<MovieDetailModel>>(NetworkResult.Loading())
    val ratedMovies = MutableStateFlow<NetworkResult<RatedMovieModel>>(NetworkResult.Loading())
    val addRating = MutableStateFlow<NetworkResult<AddRatingModel>>(NetworkResult.Loading())
    val castAndCrew =
        MutableStateFlow<NetworkResult<MovieCastAndCrewModel>>(NetworkResult.Loading())
    val movieRecommendation =
        MutableStateFlow<NetworkResult<TrendingMoviesForWeek>>(NetworkResult.Loading())
    val movieReviews = MutableStateFlow<NetworkResult<TVReviewModel>>(NetworkResult.Loading())
    val movieImages = MutableStateFlow<NetworkResult<ImagesTVModel>>(NetworkResult.Loading())
    val videosMovie = MutableStateFlow<NetworkResult<VideosModel>>(NetworkResult.Loading())


    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieDetails.emit(repo.getMovieDetails(movieId))

        }
    }


    fun getRatedMovie() {
        viewModelScope.launch {
            ratedMovies.emit(repo.getRatedMovie())
        }
    }

    fun addRating(rating: AddRating, movieId: Int) {
        viewModelScope.launch {
            addRating.emit(repo.addRating(rating, movieId))

        }
    }

    fun getMovieCastAndCrew(movieId: Int) {
        viewModelScope.launch {

            castAndCrew.emit(repo.getMovieCastAndCrew(movieId))

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


    fun getMovieRecommendation(movieId: Int) {
        viewModelScope.launch {

            movieRecommendation.emit(repo.getMovieRecommendation(movieId))

        }
    }

    fun getMovieReviews(movieId: Int) {
        viewModelScope.launch {

            movieReviews.emit(repo.getMovieReviews(movieId))

        }
    }

    fun getImagesForMovie(movieId: Int) {
        viewModelScope.launch {

            movieImages.emit(repo.getImagesForMovie(movieId))

        }
    }

    fun getVideosForMovie(movieId: Int) {
        viewModelScope.launch {
            videosMovie.emit(repo.getVideosForMovie(movieId))
        }
    }

    val youtubeVideos = videosMovie.map { networkResult ->
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