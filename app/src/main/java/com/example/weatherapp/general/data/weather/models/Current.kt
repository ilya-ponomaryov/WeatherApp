package com.example.weatherapp.general.data.weather.models

import com.google.gson.annotations.SerializedName

data class Current(
    val clouds: Int,
    @SerializedName("dew_point")
    val dewPoint: Double,
    @SerializedName("dt")
    val date: Int,
    @SerializedName("feels_like")
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val sunrise: Int,
    val sunset: Int,
    @SerializedName("temp")
    val temperature: Double,
    val uvi: Double,
    val visibility: Int,
    @SerializedName("weather")
    val aboutWeather: List<AboutWeather>,
    @SerializedName("wind_deg")
    val windDegrees: Int,
    @SerializedName("wind_gust")
    val windGust: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double
)