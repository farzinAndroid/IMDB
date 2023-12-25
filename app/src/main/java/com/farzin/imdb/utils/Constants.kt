package com.farzin.imdb.utils

import com.farzin.imdb.BuildConfig

object Constants {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val DATASTORE_NAME = "app_datastore"
    const val CREATED_AT_ASC = "created_at.asc"
    const val CREATED_AT_DESC = "created_at.desc"
    const val DB_NAME = "imdb_db"
    const val PERSON_TABLE_NAME = "person_table"
    const val MEDIA_TABLE_NAME = "favorite_table"
    const val TIME_OUT: Long = 60
    const val API_KEY = BuildConfig.API_KEY
    const val TMDB_WEBSITE = "https://www.themoviedb.org/signup?language=en-GB"
    var REQUEST_TOKEN = ""
    var SESSION_ID = ""
    const val IMAGE_APPEND = "https://image.tmdb.org/t/p/original"
    var ACC_ID = ""
    var USER_NAME = ""
    var USER_LANG = "USER_LANG"
    var PERSIAN = "fa"
    var ENGLISH = "en"



}