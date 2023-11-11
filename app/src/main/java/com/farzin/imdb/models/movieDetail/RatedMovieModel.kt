package com.farzin.imdb.models.movieDetail

data class RatedMovieModel(
    val page: Int,
    val results: List<RatedMovieModelResult>,
    val total_pages: Int,
    val total_results: Int
)
data class RatedMovieModelResult(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val rating: Double,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)