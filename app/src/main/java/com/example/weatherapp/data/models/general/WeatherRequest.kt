package com.example.weatherapp.data.models.general

import retrofit2.http.Path

data class WeatherRequest(
    val lat: Double,
    val lon: Double,
    val exclude: String,
    val units: String,
    val lang: String,
    val appid: String
)
