package com.farzin.imdb.data.remote

import com.farzin.imdb.models.home.TrendingMoviesForWeek
import com.farzin.imdb.models.movieDetail.MovieCastAndCrewModel
import com.farzin.imdb.models.movieDetail.MovieDetailModel
import com.farzin.imdb.models.movieDetail.RatedMovieModel
import com.farzin.imdb.models.movieDetail.VideosModel
import com.farzin.imdb.models.tvDetail.AddRating
import com.farzin.imdb.models.tvDetail.AddRatingModel
import com.farzin.imdb.models.tvDetail.ImagesTVModel
import com.farzin.imdb.models.tvDetail.TVReviewModel
import com.farzin.imdb.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailApiInterface {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path(
            "movie_id",
            encoded = false
        ) movieId:Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.USER_LANG,
    ): Response<MovieDetailModel>


    @GET("account/{account_id}/rated/movies")
    suspend fun getRatedMovie(
        @Path(
            "account_id",
            encoded = false
        ) accountId:Int = Constants.ACC_ID.toInt(),
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("session_id") sessionId: String = Constants.SESSION_ID,
    ):Response<RatedMovieModel>


    @POST("movie/{movie_id}/rating")
    suspend fun addRating(
        @Path(
            "movie_id",
            encoded = false
        ) movieId:Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("session_id") sessionId: String = Constants.SESSION_ID,
        @Body rating: AddRating
    ):Response<AddRatingModel>


    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCastAndCrew(
        @Path(
            "movie_id",
            encoded = false
        ) movieId:Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
    ):Response<MovieCastAndCrewModel>


    @GET("movie/{movie_id}/recommendations")
    suspend fun getMovieRecommendation(
        @Path(
            "movie_id",
            encoded = false
        ) movieId:Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = Constants.USER_LANG
    ):Response<TrendingMoviesForWeek>


    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path(
            "movie_id",
            encoded = false
        ) movieId:Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
    ):Response<TVReviewModel>

    @GET("movie/{movie_id}/images")
    suspend fun getImagesForMovie(
        @Path(
            "movie_id",
            encoded = false
        ) movieId:Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
    ):Response<ImagesTVModel>


    @GET("movie/{movie_id}/videos")
    suspend fun getVideosForMovie(
        @Path(
            "movie_id",
            encoded = false
        ) movieId:Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
    ):Response<VideosModel>



}