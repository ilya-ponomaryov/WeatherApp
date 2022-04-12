package com.example.weatherapp.general.data.weather.models

import com.google.gson.annotations.SerializedName

data class Hourly(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    val feels_like: Double,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val snow: Snow,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    @SerializedName("weather")
    val aboutWeather: List<AboutWeather>,
    val wind_deg: Int,
    val wind_gust: Double,
    val wind_speed: Double
)