package com.example.weatherapp.general.usecases.weather.models

data class AboutWeather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)