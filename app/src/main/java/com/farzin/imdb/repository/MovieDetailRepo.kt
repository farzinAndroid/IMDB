package com.farzin.imdb.repository

import com.farzin.imdb.data.remote.BaseApiResponse
import com.farzin.imdb.data.remote.MovieDetailApiInterface
import com.farzin.imdb.data.remote.NetworkResult
import com.farzin.imdb.models.home.TrendingMoviesForWeek
import com.farzin.imdb.models.movieDetail.MovieCastAndCrewModel
import com.farzin.imdb.models.movieDetail.MovieDetailModel
import com.farzin.imdb.models.movieDetail.RatedMovieModel
import com.farzin.imdb.models.tvDetail.AddRating
import com.farzin.imdb.models.tvDetail.AddRatingModel
import com.farzin.imdb.models.tvDetail.ImagesTVModel
import com.farzin.imdb.models.tvDetail.TVReviewModel
import javax.inject.Inject

class MovieDetailRepo @Inject constructor(private val api:MovieDetailApiInterface) : BaseApiResponse() {


    suspend fun getMovieDetails(movieId:Int) : NetworkResult<MovieDetailModel> = safeApiCall {
        api.getMovieDetails(movieId)
    }
    suspend fun getRatedMovie() : NetworkResult<RatedMovieModel> = safeApiCall {
        api.getRatedMovie()
    }

    suspend fun addRating(rating: AddRating,movieId: Int) : NetworkResult<AddRatingModel> = safeApiCall {
        api.addRating(rating = rating, movieId = movieId)
    }

    suspend fun getMovieCastAndCrew(movieId: Int) : NetworkResult<MovieCastAndCrewModel> = safeApiCall {
        api.getMovieCastAndCrew(movieId = movieId)
    }

    suspend fun getMovieRecommendation(movieId: Int) : NetworkResult<TrendingMoviesForWeek> = safeApiCall {
        api.getMovieRecommendation(movieId = movieId)
    }


    suspend fun getMovieReviews(movieId: Int) : NetworkResult<TVReviewModel> = safeApiCall {
        api.getMovieReviews(movieId = movieId)
    }

    suspend fun getImagesForMovie(movieId: Int) : NetworkResult<ImagesTVModel> = safeApiCall {
        api.getImagesForMovie(movieId = movieId)
    }


}