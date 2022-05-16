package com.example.weatherapp.general.usecases.weather.models

import com.google.gson.annotations.SerializedName

data class WeatherForHour(
    val clouds: Int = 0,
    @SerializedName("dew_point")
    val dewPoint: Double = 0.0,
    @SerializedName("dt")
    val hour: String = "0",
    @SerializedName("feels_like")
    val feelsLike: Double = 0.0,
    val humidity: Int = 0,
    @SerializedName("pop")
    val probabilityOfPrecipitation: Double = 0.0,
    val pressure: Int = 0,
    @SerializedName("temp")
    val temperature: String = "0",
    val uvi: Double = 0.0,
    val visibility: Int = 0,
    @SerializedName("wind_deg")
    val windDegrees: Int = 0,
    @SerializedName("wind_gust")
    val windGust: Double = 0.0,
    @SerializedName("wind_speed")
    val windSpeed: Double = 0.0,
    val weatherIcon: String = "http://openweathermap.org/img/w/" + "04d" + ".png",
)