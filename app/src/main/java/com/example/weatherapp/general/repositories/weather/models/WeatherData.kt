package com.example.weatherapp.general.repositories.weather.models

import com.google.gson.annotations.SerializedName

data class WeatherData(
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double,
    val timezone: String,
    @SerializedName("timezone_offset")
    val timezoneOffset: Int
)