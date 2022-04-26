package com.example.weatherapp.general.data.location.models

import com.google.gson.annotations.SerializedName

data class LocalNames(
    @SerializedName("ar")
    val arabian: String,
    @SerializedName("en")
    val english: String,
    @SerializedName("fr")
    val french: String,
    @SerializedName("ko")
    val korean: String,
    @SerializedName("ru")
    val russian: String,
    val uk: String
)