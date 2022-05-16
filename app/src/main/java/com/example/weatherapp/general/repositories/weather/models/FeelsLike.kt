package com.example.weatherapp.general.repositories.weather.models

import com.google.gson.annotations.SerializedName

data class FeelsLike(
    val day: Double = 0.0,
    @SerializedName("eve")
    val evening: Double = 0.0,
    @SerializedName("morn")
    val morning: Double = 0.0,
    val night: Double = 0.0
)