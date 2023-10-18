package com.farzin.imdb.models.home

data class TVBasedOnNetwork(
    val page: Int = 0,
    val results: List<TVBasedOnNetworkResult> = emptyList(),
    val total_pages: Int = 0,
    val total_results: Int = 0,
)

data class TVBasedOnNetworkResult(
    val backdrop_path: String,
    val first_air_date: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int,
)