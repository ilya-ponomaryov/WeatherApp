package com.example.weatherapp.general.data.weather.models

import com.google.gson.annotations.SerializedName

data class WeatherForHour(
    val clouds: Int,
    @SerializedName("dew_point")
    val dewPoint: Double,
    @SerializedName("dt")
    val hour: String,
    @SerializedName("feels_like")
    val feels_like: Double,
    val humidity: Int,
    @SerializedName("pop")
    val probabilityOfPrecipitation: Double,
    val pressure: Int,
    val snow: Snow,
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
)