package com.example.weatherapp.general.data.weather.models

import com.google.gson.annotations.SerializedName

data class Hourly(
    val clouds: Int,
    @SerializedName("dew_point")
    val dewPoint: Double,
    @SerializedName("dt")
    val date: Int,
    @SerializedName("feels_like")
    val feelsLike: Double,
    val humidity: Int,
    @SerializedName("pop")
    val probabilityOfPrecipitation: Double,
    val pressure: Int,
    val snow: Snow,
    @SerializedName("temp")
    val temperature: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<WeatherXX>,
    @SerializedName("wind_deg")
    val windDegrees: Int,
    @SerializedName("wind_gust")
    val windGust: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double
)