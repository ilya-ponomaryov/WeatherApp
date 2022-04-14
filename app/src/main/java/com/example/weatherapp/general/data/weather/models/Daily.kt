package com.example.weatherapp.general.data.weather.models

import com.google.gson.annotations.SerializedName

data class Daily(
    val clouds: Int,
    @SerializedName("dew_point")
    val dewPoint: Double,
    @SerializedName("dt")
    val date: Int,
    @SerializedName("feels_like")
    val feelsLike: FeelsLike,
    val humidity: Int,
    @SerializedName("moon_phase")
    val moonPhase: Double,
    val moonrise: Int,
    @SerializedName("moonset")
    val moonSet: Int,
    @SerializedName("pop")
    val probabilityOfPrecipitation: Double,
    val pressure: Int,
    val rain: Double,
    val snow: Double,
    val sunrise: Int,
    val sunset: Int,
    @SerializedName("temp")
    val temperature: Temperature,
    val uvi: Double,
    @SerializedName("weather")
    val aboutWeather: List<AboutWeather>,
    @SerializedName("wind_deg")
    val windDegrees: Int,
    @SerializedName("wind_gust")
    val windGust: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double
)