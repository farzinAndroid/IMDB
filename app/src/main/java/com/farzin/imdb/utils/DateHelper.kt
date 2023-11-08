package com.farzin.imdb.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateHelper {

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

    @SuppressLint("SimpleDateFormat")
    fun formatDate(dateString: String): String? {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("MMMM dd, yyyy")

        val parsedDate = inputFormat.parse(dateString)
        return parsedDate?.let { outputFormat.format(it) }
    }


}