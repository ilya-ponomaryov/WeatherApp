package com.example.weatherapp.general.data.location.models

data class LocationRequest(
    val q: String,
    val limit: Int,
    val appid: String
)