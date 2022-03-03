package com.example.weatherapp.general.data.models.weather

data class WeatherRequest(
    val lat: Double,
    val lon: Double,
    val exclude: String,
    val units: String,
    val lang: String,
    val appid: String
)
