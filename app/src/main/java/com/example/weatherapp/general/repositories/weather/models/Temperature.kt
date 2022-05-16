package com.example.weatherapp.general.repositories.weather.models

import com.google.gson.annotations.SerializedName

data class Temperature(
    val day: Double,
    @SerializedName("eve")
    val evening: Double,
    val max: Double,
    val min: Double,
    @SerializedName("morn")
    val morning: Double,
    val night: Double
)