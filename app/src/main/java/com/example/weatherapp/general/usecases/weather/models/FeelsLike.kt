package com.example.weatherapp.general.usecases.weather.models

import com.google.gson.annotations.SerializedName

data class FeelsLike(
    val day: Double,
    @SerializedName("eve")
    val evening: Double,
    @SerializedName("morn")
    val morning: Double,
    val night: Double
)