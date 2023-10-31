package com.farzin.imdb.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object Utils {

    fun appendImage(path:String): String{
        return "${Constants.IMAGE_APPEND}$path"
    }


    fun extractYearFromDate(dateString: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return try {
            val date = dateFormat.parse(dateString)
            val calendar = Calendar.getInstance()
            if (date != null) {
                calendar.time = date
            }
            calendar.get(Calendar.YEAR).toString()
        } catch (e: ParseException) {
            // Handle the exception here
            ""
        }
    }

}