package com.example.weatherapp.general.repositories.weather

import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    fun getDateAsString(unix: Int): String {
        val date = getDateFromUnix(unix)
        val weekDay = SimpleDateFormat("EE", Locale("ru")).format(date.time)
        val day = SimpleDateFormat("dd", Locale("ru")).format(date.time)
        val month = SimpleDateFormat("MMMM", Locale("ru")).format(date.time)
        return "$day $month, $weekDay"
    }

    private fun getDateFromUnix(unix: Int): Date {
        return Date(unix.toLong() * 1000)
    }
}