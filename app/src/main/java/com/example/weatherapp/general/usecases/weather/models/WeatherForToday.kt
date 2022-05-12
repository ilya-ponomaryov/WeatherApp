package com.example.weatherapp.general.usecases.weather.models

import com.google.gson.annotations.SerializedName

data class WeatherForToday(
    val clouds: Int,
    @SerializedName("dew_point")
    val dewPoint: Double,
    @SerializedName("dt")
    val date: String,
    @SerializedName("feels_like")
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val sunrise: Int,
    val sunset: Int,
    @SerializedName("temp")
    val temperature: String,
    val uvi: Double,
    val visibility: Int,
    @SerializedName("weather")
    val aboutWeather: List<AboutWeather>,
    @SerializedName("wind_deg")
    val windDegrees: Int,
    @SerializedName("wind_gust")
    val windGust: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    val weatherIcon: String,
    val weatherDescription: String,
)
