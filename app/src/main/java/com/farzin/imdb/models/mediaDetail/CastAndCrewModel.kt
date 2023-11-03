package com.farzin.imdb.models.mediaDetail

data class CastAndCrewModel(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int,
)

data class Cast(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?,
    val roles: List<Role>,
    val total_episode_count: Int,
)

data class Role(
    val character: String,
    val credit_id: String,
    val episode_count: Int,
)
data class Crew(
    val adult: Boolean,
    val department: String,
    val gender: Int,
    val id: Int,
    val jobs: List<Job>,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?,
    val total_episode_count: Int,
)
data class Job(
    val credit_id: String,
    val episode_count: Int,
    val job: String,
)