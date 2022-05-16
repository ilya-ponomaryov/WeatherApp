package com.example.weatherapp.general.repositories.location.models

import com.google.gson.annotations.SerializedName

data class LocationItem(
    val country: String,
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("local_names")
    val localNames: LocalNames,
    @SerializedName("lon")
    val longitude: Double,
    val name: String,
    val state: String
)