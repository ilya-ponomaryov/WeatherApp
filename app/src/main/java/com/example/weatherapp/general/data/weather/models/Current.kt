package com.example.weatherapp.general.data.weather.models

data class Current(
    val clouds: Int,
    val dew_point: Double,
    val date: Int,
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val sunrise: Int,
    val sunset: Int,
    val temperature: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<Weather>,
    val windDegrees: Int,
    val windGust: Double,
    val windSpeed: Double
)