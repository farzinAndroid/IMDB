package com.farzin.imdb.models.mediaDetail

import com.google.gson.annotations.SerializedName

data class TVDetailModel(
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    val created_by: List<CreatedBy> = emptyList(),
    val episode_run_time: List<Int> = emptyList(),
    val first_air_date: String = "",
    val genres: List<Genre> = emptyList(),
    val homepage: String = "",
    val id: Int = 0,
    val in_production: Boolean = false,
    val languages: List<String> = emptyList(),
    val last_air_date: String = "",
    val last_episode_to_air: LastEpisodeToAir = LastEpisodeToAir(),
    val name: String = "",
    val networks: List<Network> = emptyList(),
    val next_episode_to_air: Any? = null,
    val number_of_episodes: Int = 0,
    val number_of_seasons: Int = 0,
    val origin_country: List<String> = emptyList(),
    val original_language: String = "",
    val original_name: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val production_companies: List<ProductionCompany> = emptyList(),
    val production_countries: List<ProductionCountry> = emptyList(),
    val seasons: List<Season> = emptyList(),
    val spoken_languages: List<SpokenLanguage> = emptyList(),
    val status: String = "",
    val tagline: String = "",
    val type: String = "",
    val vote_average: Double = 0.0,
    val vote_count: Int = 0,
)

data class CreatedBy(
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val profile_path: String,
)

data class Genre(
    val id: Int,
    val name: String,
)

data class LastEpisodeToAir(
    val air_date: String = "",
    val episode_number: Int = 0,
    val episode_type: String = "",
    val id: Int = 0,
    val name: String = "",
    val overview: String = "",
    val production_code: String = "",
    val runtime: Int = 0,
    val season_number: Int = 0,
    val show_id: Int = 0,
    val still_path: String = "",
    val vote_average: Double = 0.0,
    val vote_count: Int = 0,
)

data class Network(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String,
)

data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String,
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String,
)

data class Season(
    val air_date: String,
    val episode_count: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String,
    val season_number: Int,
    val vote_average: Double,
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String,
)