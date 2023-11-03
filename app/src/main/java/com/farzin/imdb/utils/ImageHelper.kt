package com.farzin.imdb.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object ImageHelper {

    fun appendImage(path:String): String{
        return "${Constants.IMAGE_APPEND}$path"
    }

}