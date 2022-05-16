package com.example.weatherapp.general.usecases.weather.models

import com.google.gson.annotations.SerializedName

data class WeatherForDay(
    val clouds: Int = 0,
    @SerializedName("dew_point")
    val dewPoint: Double = 0.0,
    val date: String = "00.00.00",
    @SerializedName("feels_like")
    val feelsLike: FeelsLike = FeelsLike(),
    val humidity: Int = 0,
    @SerializedName("moon_phase")
    val moonPhase: Double = 0.0,
    val moonrise: Int = 0,
    @SerializedName("moonset")
    val moonSet: Int = 0,
    @SerializedName("pop")
    val probabilityOfPrecipitation: Double = 0.0,
    val pressure: Int = 0,
    val rain: Double = 0.0,
    val snow: Double = 0.0,
    val sunrise: Int =0,
    val sunset: Int = 0,
    val uvi: Double = 0.0,
    @SerializedName("weather")
    val aboutWeather: List<AboutWeather> = listOf(AboutWeather()),
    @SerializedName("wind_deg")
    val windDegrees: Int = 0,
    @SerializedName("wind_gust")
    val windGust: Double = 0.0,
    @SerializedName("wind_speed")
    val windSpeed: Double = 0.0,
    val weatherForHour: List<WeatherForHour> = listOf(WeatherForHour()),
    val daytimeTemperature: String = "0",
    val eveningTemperature: String = "0",
    val maxTemperature: String = "0",
    val minTemperature: String = "0",
    val morningTemperature: String = "0",
    val nighttimeTemperature: String = "0",
    val weatherIcon: String = "http://openweathermap.org/img/w/" + "04d" + ".png",
)
