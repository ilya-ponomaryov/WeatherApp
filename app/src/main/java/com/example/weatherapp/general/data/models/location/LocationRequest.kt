package com.example.weatherapp.general.data.models.location

data class LocationRequest(
    val q: String,
    val limit: Int,
    val appid: String
)