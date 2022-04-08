package com.example.weatherapp.general.data.weather.models

import com.google.gson.annotations.SerializedName

data class WeatherForDay(
    val clouds: Int,
    @SerializedName("dew_point")
    val dewPoint: Double,
    val date: String,
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
    val uvi: Double,
    val weather: List<WeatherX>,
    @SerializedName("wind_deg")
    val windDegrees: Int,
    @SerializedName("wind_gust")
    val windGust: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    val hourly: List<WeatherForHour>,
    val daytimeTemperature: String,
    val eveningTemperature: String,
    val maxTemperature: String,
    val minTemperature: String,
    val morningTemperature: String,
    val nighttimeTemperature: String,
    val weatherIcon: String,
)
