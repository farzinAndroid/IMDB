package com.farzin.imdb.models.movieDetail

import com.farzin.imdb.models.tvDetail.Genre
import com.farzin.imdb.models.tvDetail.ProductionCompany
import com.farzin.imdb.models.tvDetail.ProductionCountry
import com.farzin.imdb.models.tvDetail.SpokenLanguage

data class MovieDetailModel(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Long,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class BelongsToCollection(
    val backdrop_path: String = "",
    val id: Int = 0,
    val name: String = "",
    val poster_path: String = ""
)

