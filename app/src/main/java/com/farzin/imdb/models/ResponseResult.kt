package com.farzin.imdb.models


data class ResponseResult<T>(
    val message: String,
    val data: T,
    val success: Boolean,
)
