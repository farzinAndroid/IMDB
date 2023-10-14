package com.farzin.imdb.utils

object ImageHelper {

    fun appendImage(backdropPath:String): String{
        return "${Constants.IMAGE_APPEND}$backdropPath"
    }

}