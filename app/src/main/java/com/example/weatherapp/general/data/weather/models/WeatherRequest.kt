package com.example.weatherapp.general.data.weather.models

data class WeatherRequest(
    val lat: Double,
    val lon: Double,
    val exclude: String,
    val units: String,
    val lang: String,
    val appid: String
)
