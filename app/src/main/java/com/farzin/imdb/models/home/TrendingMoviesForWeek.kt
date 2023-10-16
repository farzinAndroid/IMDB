package com.farzin.imdb.models.home

data class TrendingMoviesForWeek(
    val page: Int = 0,
    val results: List<TrendingMoviesForWeekResult> = emptyList(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)

data class TrendingMoviesForWeekResult(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)