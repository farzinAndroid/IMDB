package com.farzin.imdb.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object Utils {

    fun appendImage(path:String): String{
        return "${Constants.IMAGE_APPEND}$path"
    }


    fun extractYearFromDate(dateString: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(dateString)
        val calendar = Calendar.getInstance()
        if (date != null) {
            calendar.time = date
        }
        return calendar.get(Calendar.YEAR).toString()
    }

}