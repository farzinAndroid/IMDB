package com.farzin.imdb.models.home

data class AddToWatchListRequest(
    val media_id: Int,
    val media_type: String,
    val watchlist: Boolean
)