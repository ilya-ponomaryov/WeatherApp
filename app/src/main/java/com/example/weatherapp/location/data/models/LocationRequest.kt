package com.example.weatherapp.location.data.models

data class LocationRequest(
    val q: String,
    val limit: Int,
    val appid: String
)